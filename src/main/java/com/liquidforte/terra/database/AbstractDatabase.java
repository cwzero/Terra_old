package com.liquidforte.terra.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

import org.h2.jdbcx.JdbcConnectionPool;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.extension.ExtensionCallback;
import org.jdbi.v3.core.extension.ExtensionConsumer;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public abstract class AbstractDatabase implements Database {
	private JdbcConnectionPool pool;

	public AbstractDatabase(String url, String username, String password) {
		pool = JdbcConnectionPool.create(url, username, password);
	}

	public Connection getConnection() throws SQLException {
		return pool.getConnection();
	}

	public void runTransaction(Consumer<Connection> transaction) {
		try (Connection conn = getConnection()) {
			transaction.accept(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void runJdbiTransaction(Consumer<Jdbi> jdbiTransaction) {
		runTransaction(conn -> {
			Jdbi jdbi = Jdbi.create(conn);
			jdbi.installPlugin(new SqlObjectPlugin());
			jdbiTransaction.accept(jdbi);
		});
	}

	public <E, R> R withJdbiExtension(Class<E> extensionType, ExtensionCallback<R, E, Exception> callback)
			throws Exception {
		R result = null;

		try (Connection conn = getConnection()) {
			Jdbi jdbi = Jdbi.create(conn);
			jdbi.installPlugin(new SqlObjectPlugin());
			result = jdbi.withExtension(extensionType, callback);
		}

		return result;
	}

	public <E> void useJdbiExtension(Class<E> extensionType, ExtensionConsumer<E, Exception> consumer)
			throws Exception {
		try (Connection conn = getConnection()) {
			Jdbi jdbi = Jdbi.create(conn);
			jdbi.installPlugin(new SqlObjectPlugin());
			jdbi.useExtension(extensionType, consumer);
		}
	}
}
