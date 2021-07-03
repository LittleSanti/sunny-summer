package com.samajackun.summer.shared.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.util.Map;
import java.util.WeakHashMap;

public class ServerDatabaseMetaDataImpl implements Serializable, DatabaseMetaData
{
	private static final long serialVersionUID=5888020406411654108L;

	private static final Map<Integer, DatabaseMetaData> INSTANCES=new WeakHashMap<Integer, DatabaseMetaData>(4096);

	private final Integer instanceIndex;

	private transient DatabaseMetaData src;

	private final ServerConnectionImpl owner;

	public ServerDatabaseMetaDataImpl(ServerConnectionImpl connection, DatabaseMetaData src)
	{
		super();
		synchronized (INSTANCES)
		{
			this.instanceIndex=INSTANCES.size();
			INSTANCES.put(INSTANCES.size(), src);
		}
		this.owner=connection;
		this.src=src;
	}

	final DatabaseMetaData source()
	{
		if (this.src == null)
		{
			this.src=INSTANCES.get(this.instanceIndex);
		}
		return this.src;
	}

	@Override
	public boolean allProceduresAreCallable()
		throws SQLException
	{
		return source().allProceduresAreCallable();
	}

	@Override
	public boolean allTablesAreSelectable()
		throws SQLException
	{
		return source().allTablesAreSelectable();
	}

	@Override
	public String getURL()
		throws SQLException
	{
		return source().getURL();
	}

	@Override
	public String getUserName()
		throws SQLException
	{
		return source().getUserName();
	}

	@Override
	public boolean isReadOnly()
		throws SQLException
	{
		return source().isReadOnly();
	}

	@Override
	public boolean nullsAreSortedHigh()
		throws SQLException
	{
		return source().nullsAreSortedHigh();
	}

	@Override
	public boolean nullsAreSortedLow()
		throws SQLException
	{
		return source().nullsAreSortedLow();
	}

	@Override
	public boolean nullsAreSortedAtStart()
		throws SQLException
	{
		return source().nullsAreSortedAtStart();
	}

	@Override
	public boolean nullsAreSortedAtEnd()
		throws SQLException
	{
		return source().nullsAreSortedAtEnd();
	}

	@Override
	public String getDatabaseProductName()
		throws SQLException
	{
		return source().getDatabaseProductName();
	}

	@Override
	public String getDatabaseProductVersion()
		throws SQLException
	{
		return source().getDatabaseProductVersion();
	}

	@Override
	public String getDriverName()
		throws SQLException
	{
		return source().getDriverName();
	}

	@Override
	public String getDriverVersion()
		throws SQLException
	{
		return source().getDriverVersion();
	}

	@Override
	public int getDriverMajorVersion()
	{
		return source().getDriverMajorVersion();
	}

	@Override
	public int getDriverMinorVersion()
	{
		return source().getDriverMinorVersion();
	}

	@Override
	public boolean usesLocalFiles()
		throws SQLException
	{
		return source().usesLocalFiles();
	}

	@Override
	public boolean usesLocalFilePerTable()
		throws SQLException
	{
		return source().usesLocalFilePerTable();
	}

	@Override
	public boolean supportsMixedCaseIdentifiers()
		throws SQLException
	{
		return source().supportsMixedCaseIdentifiers();
	}

	@Override
	public boolean storesUpperCaseIdentifiers()
		throws SQLException
	{
		return source().storesUpperCaseIdentifiers();
	}

	@Override
	public boolean storesLowerCaseIdentifiers()
		throws SQLException
	{
		return source().storesLowerCaseIdentifiers();
	}

	@Override
	public boolean storesMixedCaseIdentifiers()
		throws SQLException
	{
		return source().storesMixedCaseIdentifiers();
	}

	@Override
	public boolean supportsMixedCaseQuotedIdentifiers()
		throws SQLException
	{
		return source().supportsMixedCaseQuotedIdentifiers();
	}

	@Override
	public boolean storesUpperCaseQuotedIdentifiers()
		throws SQLException
	{
		return source().storesUpperCaseQuotedIdentifiers();
	}

	@Override
	public boolean storesLowerCaseQuotedIdentifiers()
		throws SQLException
	{
		return source().storesLowerCaseQuotedIdentifiers();
	}

	@Override
	public boolean storesMixedCaseQuotedIdentifiers()
		throws SQLException
	{
		return source().storesMixedCaseQuotedIdentifiers();
	}

	@Override
	public String getIdentifierQuoteString()
		throws SQLException
	{
		return source().getIdentifierQuoteString();
	}

	@Override
	public String getSQLKeywords()
		throws SQLException
	{
		return source().getSQLKeywords();
	}

	@Override
	public String getNumericFunctions()
		throws SQLException
	{
		return source().getNumericFunctions();
	}

	@Override
	public String getStringFunctions()
		throws SQLException
	{
		return source().getStringFunctions();
	}

	@Override
	public String getSystemFunctions()
		throws SQLException
	{
		return source().getSystemFunctions();
	}

	@Override
	public String getTimeDateFunctions()
		throws SQLException
	{
		return source().getTimeDateFunctions();
	}

	@Override
	public String getSearchStringEscape()
		throws SQLException
	{
		return source().getSearchStringEscape();
	}

	@Override
	public String getExtraNameCharacters()
		throws SQLException
	{
		return source().getExtraNameCharacters();
	}

	@Override
	public boolean supportsAlterTableWithAddColumn()
		throws SQLException
	{
		return source().supportsAlterTableWithAddColumn();
	}

	@Override
	public boolean supportsAlterTableWithDropColumn()
		throws SQLException
	{
		return source().supportsAlterTableWithDropColumn();
	}

	@Override
	public boolean supportsColumnAliasing()
		throws SQLException
	{
		return source().supportsColumnAliasing();
	}

	@Override
	public boolean nullPlusNonNullIsNull()
		throws SQLException
	{
		return source().nullPlusNonNullIsNull();
	}

	@Override
	public boolean supportsConvert()
		throws SQLException
	{
		return source().supportsConvert();
	}

	@Override
	public boolean supportsConvert(int fromType, int toType)
		throws SQLException
	{
		return source().supportsConvert(fromType, toType);
	}

	@Override
	public boolean supportsTableCorrelationNames()
		throws SQLException
	{
		return source().supportsTableCorrelationNames();
	}

	@Override
	public boolean supportsDifferentTableCorrelationNames()
		throws SQLException
	{
		return source().supportsDifferentTableCorrelationNames();
	}

	@Override
	public boolean supportsExpressionsInOrderBy()
		throws SQLException
	{
		return source().supportsExpressionsInOrderBy();
	}

	@Override
	public boolean supportsOrderByUnrelated()
		throws SQLException
	{
		return source().supportsOrderByUnrelated();
	}

	@Override
	public boolean supportsGroupBy()
		throws SQLException
	{
		return source().supportsGroupBy();
	}

	@Override
	public boolean supportsGroupByUnrelated()
		throws SQLException
	{
		return source().supportsGroupByUnrelated();
	}

	@Override
	public boolean supportsGroupByBeyondSelect()
		throws SQLException
	{
		return source().supportsGroupByBeyondSelect();
	}

	@Override
	public boolean supportsLikeEscapeClause()
		throws SQLException
	{
		return source().supportsLikeEscapeClause();
	}

	@Override
	public boolean supportsMultipleResultSets()
		throws SQLException
	{
		return source().supportsMultipleResultSets();
	}

	@Override
	public boolean supportsMultipleTransactions()
		throws SQLException
	{
		return source().supportsMultipleTransactions();
	}

	@Override
	public boolean supportsNonNullableColumns()
		throws SQLException
	{
		return source().supportsNonNullableColumns();
	}

	@Override
	public boolean supportsMinimumSQLGrammar()
		throws SQLException
	{
		return source().supportsMinimumSQLGrammar();
	}

	@Override
	public boolean supportsCoreSQLGrammar()
		throws SQLException
	{
		return source().supportsCoreSQLGrammar();
	}

	@Override
	public boolean supportsExtendedSQLGrammar()
		throws SQLException
	{
		return source().supportsExtendedSQLGrammar();
	}

	@Override
	public boolean supportsANSI92EntryLevelSQL()
		throws SQLException
	{
		return source().supportsANSI92EntryLevelSQL();
	}

	@Override
	public boolean supportsANSI92IntermediateSQL()
		throws SQLException
	{
		return source().supportsANSI92IntermediateSQL();
	}

	@Override
	public boolean supportsANSI92FullSQL()
		throws SQLException
	{
		return source().supportsANSI92FullSQL();
	}

	@Override
	public boolean supportsIntegrityEnhancementFacility()
		throws SQLException
	{
		return source().supportsIntegrityEnhancementFacility();
	}

	@Override
	public boolean supportsOuterJoins()
		throws SQLException
	{
		return source().supportsOuterJoins();
	}

	@Override
	public boolean supportsFullOuterJoins()
		throws SQLException
	{
		return source().supportsFullOuterJoins();
	}

	@Override
	public boolean supportsLimitedOuterJoins()
		throws SQLException
	{
		return source().supportsLimitedOuterJoins();
	}

	@Override
	public String getSchemaTerm()
		throws SQLException
	{
		return source().getSchemaTerm();
	}

	@Override
	public String getProcedureTerm()
		throws SQLException
	{
		return source().getProcedureTerm();
	}

	@Override
	public String getCatalogTerm()
		throws SQLException
	{
		return source().getCatalogTerm();
	}

	@Override
	public boolean isCatalogAtStart()
		throws SQLException
	{
		return source().isCatalogAtStart();
	}

	@Override
	public String getCatalogSeparator()
		throws SQLException
	{
		return source().getCatalogSeparator();
	}

	@Override
	public boolean supportsSchemasInDataManipulation()
		throws SQLException
	{
		return source().supportsSchemasInDataManipulation();
	}

	@Override
	public boolean supportsSchemasInProcedureCalls()
		throws SQLException
	{
		return source().supportsSchemasInProcedureCalls();
	}

	@Override
	public boolean supportsSchemasInTableDefinitions()
		throws SQLException
	{
		return source().supportsSchemasInTableDefinitions();
	}

	@Override
	public boolean supportsSchemasInIndexDefinitions()
		throws SQLException
	{
		return source().supportsSchemasInIndexDefinitions();
	}

	@Override
	public boolean supportsSchemasInPrivilegeDefinitions()
		throws SQLException
	{
		return source().supportsSchemasInPrivilegeDefinitions();
	}

	@Override
	public boolean supportsCatalogsInDataManipulation()
		throws SQLException
	{
		return source().supportsCatalogsInDataManipulation();
	}

	@Override
	public boolean supportsCatalogsInProcedureCalls()
		throws SQLException
	{
		return source().supportsCatalogsInProcedureCalls();
	}

	@Override
	public boolean supportsCatalogsInTableDefinitions()
		throws SQLException
	{
		return source().supportsCatalogsInTableDefinitions();
	}

	@Override
	public boolean supportsCatalogsInIndexDefinitions()
		throws SQLException
	{
		return source().supportsCatalogsInIndexDefinitions();
	}

	@Override
	public boolean supportsCatalogsInPrivilegeDefinitions()
		throws SQLException
	{
		return source().supportsCatalogsInPrivilegeDefinitions();
	}

	@Override
	public boolean supportsPositionedDelete()
		throws SQLException
	{
		return source().supportsPositionedDelete();
	}

	@Override
	public boolean supportsPositionedUpdate()
		throws SQLException
	{
		return source().supportsPositionedUpdate();
	}

	@Override
	public boolean supportsSelectForUpdate()
		throws SQLException
	{
		return source().supportsSelectForUpdate();
	}

	@Override
	public boolean supportsStoredProcedures()
		throws SQLException
	{
		return source().supportsStoredProcedures();
	}

	@Override
	public boolean supportsSubqueriesInComparisons()
		throws SQLException
	{
		return source().supportsSubqueriesInComparisons();
	}

	@Override
	public boolean supportsSubqueriesInExists()
		throws SQLException
	{
		return source().supportsSubqueriesInExists();
	}

	@Override
	public boolean supportsSubqueriesInIns()
		throws SQLException
	{
		return source().supportsSubqueriesInIns();
	}

	@Override
	public boolean supportsSubqueriesInQuantifieds()
		throws SQLException
	{
		return source().supportsSubqueriesInQuantifieds();
	}

	@Override
	public boolean supportsCorrelatedSubqueries()
		throws SQLException
	{
		return source().supportsCorrelatedSubqueries();
	}

	@Override
	public boolean supportsUnion()
		throws SQLException
	{
		return source().supportsUnion();
	}

	@Override
	public boolean supportsUnionAll()
		throws SQLException
	{
		return source().supportsUnionAll();
	}

	@Override
	public boolean supportsOpenCursorsAcrossCommit()
		throws SQLException
	{
		return source().supportsOpenCursorsAcrossCommit();
	}

	@Override
	public boolean supportsOpenCursorsAcrossRollback()
		throws SQLException
	{
		return source().supportsOpenCursorsAcrossRollback();
	}

	@Override
	public boolean supportsOpenStatementsAcrossCommit()
		throws SQLException
	{
		return source().supportsOpenStatementsAcrossCommit();
	}

	@Override
	public boolean supportsOpenStatementsAcrossRollback()
		throws SQLException
	{
		return source().supportsOpenStatementsAcrossRollback();
	}

	@Override
	public int getMaxBinaryLiteralLength()
		throws SQLException
	{
		return source().getMaxBinaryLiteralLength();
	}

	@Override
	public int getMaxCharLiteralLength()
		throws SQLException
	{
		return source().getMaxCharLiteralLength();
	}

	@Override
	public int getMaxColumnNameLength()
		throws SQLException
	{
		return source().getMaxColumnNameLength();
	}

	@Override
	public int getMaxColumnsInGroupBy()
		throws SQLException
	{
		return source().getMaxColumnsInGroupBy();
	}

	@Override
	public int getMaxColumnsInIndex()
		throws SQLException
	{
		return source().getMaxColumnsInIndex();
	}

	@Override
	public int getMaxColumnsInOrderBy()
		throws SQLException
	{
		return source().getMaxColumnsInOrderBy();
	}

	@Override
	public int getMaxColumnsInSelect()
		throws SQLException
	{
		return source().getMaxColumnsInSelect();
	}

	@Override
	public int getMaxColumnsInTable()
		throws SQLException
	{
		return source().getMaxColumnsInTable();
	}

	@Override
	public int getMaxConnections()
		throws SQLException
	{
		return source().getMaxConnections();
	}

	@Override
	public int getMaxCursorNameLength()
		throws SQLException
	{
		return source().getMaxCursorNameLength();
	}

	@Override
	public int getMaxIndexLength()
		throws SQLException
	{
		return source().getMaxIndexLength();
	}

	@Override
	public int getMaxSchemaNameLength()
		throws SQLException
	{
		return source().getMaxSchemaNameLength();
	}

	@Override
	public int getMaxProcedureNameLength()
		throws SQLException
	{
		return source().getMaxProcedureNameLength();
	}

	@Override
	public int getMaxCatalogNameLength()
		throws SQLException
	{
		return source().getMaxCatalogNameLength();
	}

	@Override
	public int getMaxRowSize()
		throws SQLException
	{
		return source().getMaxRowSize();
	}

	@Override
	public boolean doesMaxRowSizeIncludeBlobs()
		throws SQLException
	{
		return source().doesMaxRowSizeIncludeBlobs();
	}

	@Override
	public int getMaxStatementLength()
		throws SQLException
	{
		return source().getMaxStatementLength();
	}

	@Override
	public int getMaxStatements()
		throws SQLException
	{
		return source().getMaxStatements();
	}

	@Override
	public int getMaxTableNameLength()
		throws SQLException
	{
		return source().getMaxTableNameLength();
	}

	@Override
	public int getMaxTablesInSelect()
		throws SQLException
	{
		return source().getMaxTablesInSelect();
	}

	@Override
	public int getMaxUserNameLength()
		throws SQLException
	{
		return source().getMaxUserNameLength();
	}

	@Override
	public int getDefaultTransactionIsolation()
		throws SQLException
	{
		return source().getDefaultTransactionIsolation();
	}

	@Override
	public boolean supportsTransactions()
		throws SQLException
	{
		return source().supportsTransactions();
	}

	@Override
	public boolean supportsTransactionIsolationLevel(int level)
		throws SQLException
	{
		return source().supportsTransactionIsolationLevel(level);
	}

	@Override
	public boolean supportsDataDefinitionAndDataManipulationTransactions()
		throws SQLException
	{
		return source().supportsDataDefinitionAndDataManipulationTransactions();
	}

	@Override
	public boolean supportsDataManipulationTransactionsOnly()
		throws SQLException
	{
		return source().supportsDataManipulationTransactionsOnly();
	}

	@Override
	public boolean dataDefinitionCausesTransactionCommit()
		throws SQLException
	{
		return source().dataDefinitionCausesTransactionCommit();
	}

	@Override
	public boolean dataDefinitionIgnoredInTransactions()
		throws SQLException
	{
		return source().dataDefinitionIgnoredInTransactions();
	}

	@Override
	public ResultSet getProcedures(String catalog, String schemaPattern, String procedureNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getProcedures(catalog, schemaPattern, procedureNamePattern));
	}

	@Override
	public ResultSet getProcedureColumns(String catalog, String schemaPattern, String procedureNamePattern, String columnNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getProcedureColumns(catalog, schemaPattern, procedureNamePattern, columnNamePattern));
	}

	@Override
	public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getTables(catalog, schemaPattern, tableNamePattern, types));
	}

	@Override
	public ResultSet getSchemas()
		throws SQLException
	{
		return new ServerResultSetImpl(source().getSchemas());
	}

	@Override
	public ResultSet getCatalogs()
		throws SQLException
	{
		return new ServerResultSetImpl(source().getCatalogs());
	}

	@Override
	public ResultSet getTableTypes()
		throws SQLException
	{
		return new ServerResultSetImpl(source().getTableTypes());
	}

	@Override
	public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern));
	}

	@Override
	public ResultSet getColumnPrivileges(String catalog, String schema, String table, String columnNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getColumnPrivileges(catalog, schema, table, columnNamePattern));
	}

	@Override
	public ResultSet getTablePrivileges(String catalog, String schemaPattern, String tableNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getTablePrivileges(catalog, schemaPattern, tableNamePattern));
	}

	@Override
	public ResultSet getBestRowIdentifier(String catalog, String schema, String table, int scope, boolean nullable)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getBestRowIdentifier(catalog, schema, table, scope, nullable));
	}

	@Override
	public ResultSet getVersionColumns(String catalog, String schema, String table)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getVersionColumns(catalog, schema, table));
	}

	@Override
	public ResultSet getPrimaryKeys(String catalog, String schema, String table)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getPrimaryKeys(catalog, schema, table));
	}

	@Override
	public ResultSet getImportedKeys(String catalog, String schema, String table)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getImportedKeys(catalog, schema, table));
	}

	@Override
	public ResultSet getExportedKeys(String catalog, String schema, String table)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getExportedKeys(catalog, schema, table));
	}

	@Override
	public ResultSet getCrossReference(String parentCatalog, String parentSchema, String parentTable, String foreignCatalog, String foreignSchema, String foreignTable)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getCrossReference(parentCatalog, parentSchema, parentTable, foreignCatalog, foreignSchema, foreignTable));
	}

	@Override
	public ResultSet getTypeInfo()
		throws SQLException
	{
		return new ServerResultSetImpl(source().getTypeInfo());
	}

	@Override
	public ResultSet getIndexInfo(String catalog, String schema, String table, boolean unique, boolean approximate)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getIndexInfo(catalog, schema, table, unique, approximate));
	}

	@Override
	public boolean supportsResultSetType(int type)
		throws SQLException
	{
		return source().supportsResultSetType(type);
	}

	@Override
	public boolean supportsResultSetConcurrency(int type, int concurrency)
		throws SQLException
	{
		return source().supportsResultSetConcurrency(type, concurrency);
	}

	@Override
	public boolean ownUpdatesAreVisible(int type)
		throws SQLException
	{
		return source().ownUpdatesAreVisible(type);
	}

	@Override
	public boolean ownDeletesAreVisible(int type)
		throws SQLException
	{
		return source().ownDeletesAreVisible(type);
	}

	@Override
	public boolean ownInsertsAreVisible(int type)
		throws SQLException
	{
		return source().ownInsertsAreVisible(type);
	}

	@Override
	public boolean othersUpdatesAreVisible(int type)
		throws SQLException
	{
		return source().othersUpdatesAreVisible(type);
	}

	@Override
	public boolean othersDeletesAreVisible(int type)
		throws SQLException
	{
		return source().othersDeletesAreVisible(type);
	}

	@Override
	public boolean othersInsertsAreVisible(int type)
		throws SQLException
	{
		return source().othersInsertsAreVisible(type);
	}

	@Override
	public boolean updatesAreDetected(int type)
		throws SQLException
	{
		return source().updatesAreDetected(type);
	}

	@Override
	public boolean deletesAreDetected(int type)
		throws SQLException
	{
		return source().deletesAreDetected(type);
	}

	@Override
	public boolean insertsAreDetected(int type)
		throws SQLException
	{
		return source().insertsAreDetected(type);
	}

	@Override
	public boolean supportsBatchUpdates()
		throws SQLException
	{
		return source().supportsBatchUpdates();
	}

	@Override
	public ResultSet getUDTs(String catalog, String schemaPattern, String typeNamePattern, int[] types)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getUDTs(catalog, schemaPattern, typeNamePattern, types));
	}

	@Override
	public Connection getConnection()
		throws SQLException
	{
		return this.owner;
	}

	@Override
	public boolean supportsSavepoints()
		throws SQLException
	{
		return source().supportsSavepoints();
	}

	@Override
	public boolean supportsNamedParameters()
		throws SQLException
	{
		return source().supportsNamedParameters();
	}

	@Override
	public boolean supportsMultipleOpenResults()
		throws SQLException
	{
		return source().supportsMultipleOpenResults();
	}

	@Override
	public boolean supportsGetGeneratedKeys()
		throws SQLException
	{
		return source().supportsGetGeneratedKeys();
	}

	@Override
	public ResultSet getSuperTypes(String catalog, String schemaPattern, String typeNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getSuperTypes(catalog, schemaPattern, typeNamePattern));
	}

	@Override
	public ResultSet getSuperTables(String catalog, String schemaPattern, String tableNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getSuperTables(catalog, schemaPattern, tableNamePattern));
	}

	@Override
	public ResultSet getAttributes(String catalog, String schemaPattern, String typeNamePattern, String attributeNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getAttributes(catalog, schemaPattern, typeNamePattern, attributeNamePattern));
	}

	@Override
	public boolean supportsResultSetHoldability(int holdability)
		throws SQLException
	{
		return source().supportsResultSetHoldability(holdability);
	}

	@Override
	public int getResultSetHoldability()
		throws SQLException
	{
		return source().getResultSetHoldability();
	}

	@Override
	public int getDatabaseMajorVersion()
		throws SQLException
	{
		return source().getDatabaseMajorVersion();
	}

	@Override
	public int getDatabaseMinorVersion()
		throws SQLException
	{
		return source().getDatabaseMinorVersion();
	}

	@Override
	public int getJDBCMajorVersion()
		throws SQLException
	{
		return source().getJDBCMajorVersion();
	}

	@Override
	public int getJDBCMinorVersion()
		throws SQLException
	{
		return source().getJDBCMinorVersion();
	}

	@Override
	public int getSQLStateType()
		throws SQLException
	{
		return source().getSQLStateType();
	}

	@Override
	public boolean supportsStatementPooling()
		throws SQLException
	{
		return source().supportsStatementPooling();
	}

	@Override
	public RowIdLifetime getRowIdLifetime()
		throws SQLException
	{
		return source().getRowIdLifetime();
	}

	@Override
	public ResultSet getSchemas(String catalog, String schemaPattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getSchemas(catalog, schemaPattern));
	}

	@Override
	public boolean supportsStoredFunctionsUsingCallSyntax()
		throws SQLException
	{
		return source().supportsStoredFunctionsUsingCallSyntax();
	}

	@Override
	public boolean autoCommitFailureClosesAllResultSets()
		throws SQLException
	{
		return source().autoCommitFailureClosesAllResultSets();
	}

	@Override
	public ResultSet getClientInfoProperties()
		throws SQLException
	{
		return new ServerResultSetImpl(source().getClientInfoProperties());
	}

	@Override
	public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getFunctions(catalog, schemaPattern, functionNamePattern));
	}

	@Override
	public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern, String columnNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getFunctionColumns(catalog, schemaPattern, functionNamePattern, columnNamePattern));
	}

	@Override
	public boolean isWrapperFor(Class<?> iface)
		throws SQLException
	{
		return source().isWrapperFor(iface);
	}

	@Override
	public boolean locatorsUpdateCopy()
		throws SQLException
	{
		return source().locatorsUpdateCopy();
	}

	@Override
	public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
		throws SQLException
	{
		return new ServerResultSetImpl(source().getPseudoColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern));
	}

	@Override
	public boolean generatedKeyAlwaysReturned()
		throws SQLException
	{
		return source().generatedKeyAlwaysReturned();
	}

	@Override
	public <T> T unwrap(Class<T> iface)
		throws SQLException
	{
		return source().unwrap(iface);
	}

}
