class DBHelper(context: Context) : SQLiteOpenHelper(context, "LanchoneteDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createProdutoTable = """
            CREATE TABLE Produto (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT,
                preco REAL,
                quantidadeEstoque INTEGER
            )
        """
        val createVendaTable = """
            CREATE TABLE Venda (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                produtoId INTEGER,
                quantidadeVendida INTEGER,
                data TEXT,
                FOREIGN KEY(produtoId) REFERENCES Produto(id)
            )
        """
        db.execSQL(createProdutoTable)
        db.execSQL(createVendaTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Produto")
        db.execSQL("DROP TABLE IF EXISTS Venda")
        onCreate(db)
    }
}
