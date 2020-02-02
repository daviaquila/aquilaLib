package br.com.integrade.imi.da.util

//import br.com.integrade.imi.da.modelo.Coleta
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.util.*

class SqlDbMngr(var ctx: Context, private val basePath: String) {
    var helper: CustomSQLiteOpenHelper? = null
    private var bd: SQLiteDatabase? = null
    private var bdi: SQLiteDatabase? = null
    //private val funcoes: Funcoes? = null
    private val tabelasInternas = arrayOf(
        "MOB10211",
        "MOBFTPCONF",
        "MOBPLAQUETAS",
        "MOBCADASTROPLAQUETAS",
        "MOBSYNC",
        "MOBDADOSBD"
    )
    private val BD_N = "IAM"
    private val BD_V = 9

    private fun openDBi(): Boolean {
        bdi = helper!!.writableDatabase
        return bdi!!.isOpen()
    }

    private fun closeDBi() {
        bdi!!.close()
    }

    private fun openDB(): Boolean {

        var arquivoBD = File("$basePath/ISM/ism.db3")
        return if (arquivoBD.exists()) {
            bd = SQLiteDatabase.openDatabase(
                "$basePath/ISM/ism.db3",
                null,
                SQLiteDatabase.OPEN_READWRITE
            )
            //Log.i("conexao", "Banco aberto");
            bd!!.isOpen()
        } else { //Log.i("conexao", "Banco nao da pra abrir");
            false
        }
    }

    private fun closeDB() {
        bd!!.close()
        //Log.i("conexao", "Banco fechado");
    }

    fun getRowsArrayWhereOrder(
        _tabela: String,
        _campos: Array<String?>?,
        _condicao: String?,
        _vCondicao: Array<String?>?,
        _order: String?
    ): ArrayList<Any> {
        val rowArray = ArrayList<Any>()
        var colArray: ArrayList<Any?>
        val cursor: Cursor
        if (_tabela.substring(0, 3).equals("MOB", ignoreCase = true)) {
            if (openDBi()) {
                try { //cursor = bd.query(_tabela, _campos, _condicao, _vCondicao, null, null, _ordem, null);
                    cursor =
                        bdi!!.query(
                            _tabela,
                            _campos,
                            _condicao,
                            _vCondicao,
                            null,
                            null,
                            _order,
                            null
                        )
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        do {
                            colArray = ArrayList()
                            for (i in 0 until cursor.columnCount) {
                                colArray.add(cursor.getString(i))
                            }
                            rowArray.add(colArray)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getRowsArray ", e.toString())
                    e.printStackTrace()
                }
                closeDBi()
            }
        } else {
            if (openDB()) {
                try { //cursor = bd.query(_tabela, _campos, _condicao, _vCondicao, null, null, _ordem, null);
                    cursor =
                        bd!!.query(
                            _tabela,
                            _campos,
                            _condicao,
                            _vCondicao,
                            null,
                            null,
                            _order,
                            null
                        )
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        do {
                            colArray = ArrayList()
                            for (i in 0 until cursor.columnCount) {
                                colArray.add(cursor.getString(i))
                            }
                            rowArray.add(colArray)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getRowsArray ", e.toString())
                    e.printStackTrace()
                }
                closeDB()
            }
        }
        return rowArray
    }

    fun getRowsArrayOrder(
        _tabela: String,
        _campos: Array<String?>?,
        _order: String?
    ): ArrayList<Any> {
        val rowArray = ArrayList<Any>()
        var colArray: ArrayList<Any?>
        val cursor: Cursor
        if (_tabela.substring(0, 3).equals("MOB", ignoreCase = true)) {
            if (openDBi()) {
                try { //cursor = bd.query(_tabela, _campos, _condicao, _vCondicao, null, null, _ordem, null);
                    cursor = bdi!!.query(_tabela, _campos, null, null, null, null, _order, null)
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        do {
                            colArray = ArrayList()
                            for (i in 0 until cursor.columnCount) {
                                colArray.add(cursor.getString(i))
                            }
                            rowArray.add(colArray)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getRowsArray ", e.toString())
                    e.printStackTrace()
                }
                closeDBi()
            }
        } else {
            if (openDB()) {
                try { //cursor = bd.query(_tabela, _campos, _condicao, _vCondicao, null, null, _ordem, null);
                    cursor = bd!!.query(_tabela, _campos, null, null, null, null, _order, null)
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        do {
                            colArray = ArrayList()
                            for (i in 0 until cursor.columnCount) {
                                colArray.add(cursor.getString(i))
                            }
                            rowArray.add(colArray)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getRowsArray ", e.toString())
                    e.printStackTrace()
                }
                closeDB()
            }
        }
        return rowArray
    }

    fun getRowsArray(
        _tabela: String,
        _campos: Array<String?>?
    ): ArrayList<Any> {
        val rowArray = ArrayList<Any>()
        var colArray: ArrayList<Any?>
        val cursor: Cursor
        if (_tabela.substring(0, 3).equals("MOB", ignoreCase = true)) {
            if (openDBi()) {
                try { //cursor = bd.query(_tabela, _campos, _condicao, _vCondicao, null, null, _ordem, null);
                    cursor = bdi!!.query(_tabela, _campos, null, null, null, null, null, null)
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        do {
                            colArray = ArrayList()
                            for (i in 0 until cursor.columnCount) {
                                colArray.add(cursor.getString(i))
                            }
                            rowArray.add(colArray)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getRowsArray ", e.toString())
                    e.printStackTrace()
                }
                closeDBi()
            }
        } else {
            if (openDB()) {
                try { //cursor = bd.query(_tabela, _campos, _condicao, _vCondicao, null, null, _ordem, null);
                    cursor = bd!!.query(_tabela, _campos, null, null, null, null, null, null)
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        do {
                            colArray = ArrayList()
                            for (i in 0 until cursor.columnCount) {
                                colArray.add(cursor.getString(i))
                            }
                            rowArray.add(colArray)
                        } while (cursor.moveToNext())
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getRowsArray ", e.toString())
                    e.printStackTrace()
                }
                closeDB()
            }
        }
        return rowArray
    }



    fun getSingleResult(
        _tabela: String,
        _query: String?,
        _vCondicao: Array<String?>?
    ): String {
        val cursor: Cursor
        var singleRow = ""
        if (_tabela.substring(0, 3).equals("MOB", ignoreCase = true)) {
            if (openDBi()) {
                try {
                    cursor = bdi!!.rawQuery(_query, _vCondicao)
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        singleRow = cursor.getString(0)
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getSingle ", e.toString())
                    e.printStackTrace()
                }
                closeDBi()
            }
        } else {
            if (openDB()) {
                try {
                    cursor = bd!!.rawQuery(_query, _vCondicao)
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        singleRow = cursor.getString(0)
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getSingle ", e.toString())
                    e.printStackTrace()
                }
                closeDB()
            }
        }
        return singleRow
    }

    fun getSingleResultObject(
        _tabela: String,
        _query: String?,
        _vCondicao: Array<String?>?
    ): ArrayList<Any?> {
        val cursor: Cursor
        var colArray = ArrayList<Any?>()
        if (_tabela.substring(0, 3).equals("MOB", ignoreCase = true)) {
            if (openDBi()) {
                try {
                    cursor = bdi!!.rawQuery(_query, _vCondicao)
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        colArray = ArrayList()
                        for (i in 0 until cursor.columnCount) {
                            colArray.add(cursor.getString(i))
                        }
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getSingle ", e.toString())
                    e.printStackTrace()
                }
                closeDBi()
            }
        } else {
            if (openDB()) {
                try {
                    cursor = bd!!.rawQuery(_query, _vCondicao)
                    cursor.moveToFirst()
                    if (!cursor.isAfterLast) {
                        colArray = ArrayList()
                        for (i in 0 until cursor.columnCount) {
                            colArray.add(cursor.getString(i))
                        }
                    }
                    cursor.close()
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.getSingle ", e.toString())
                    e.printStackTrace()
                }
                closeDB()
            }
        }
        return colArray
    }

    fun addRow(_table: String, _camposValores: ContentValues?): Boolean {
        var sucesso = false
        Log.i("conexao", _table + "")
        if (_table.substring(0, 3).equals("MOB", ignoreCase = true)) {
            Log.i("conexao", openDBi().toString() + "")
            if (openDBi()) {
                sucesso = try {
                    if (bdi!!.insert(_table, null, _camposValores) == -1L) {
                        false
                    } else {
                        true
                    }
                } catch (e: SQLException) {
                    Log.e("I SqlDbMngr.addRow ", e.toString())
                    e.printStackTrace()
                    false
                }
                closeDBi()
            } else {
                sucesso = false
            }
        } else {
            Log.i("conexao", openDB().toString() + "")
            if (openDB()) {
                try {
                    val rId = bd!!.insert(_table, null, _camposValores)
                    Log.i("conexao", "valor do rId = ")
                    Log.i("conexao", rId.toString() + "")
                    sucesso = if (rId == -1L) {
                        false
                    } else {
                        true
                    }
                } catch (e: SQLiteException) {
                    Log.i("conexao", "SQLiteException e")
                    e.printStackTrace()
                } catch (e: SQLException) {
                    Log.e("E SqlDbMngr.addRow ", e.toString())
                    e.printStackTrace()
                    sucesso = false
                }
                closeDB()
            } else {
                sucesso = false
            }
        }
        return sucesso
    }

    fun updateRow(
        _table: String,
        _camposValores: ContentValues?,
        _chave: String?,
        _chaveValores: Array<String?>?
    ): Boolean { /*
        Log.i("_table",_table);
        Log.i("_camposValores",_camposValores.toString());
        Log.i("_chave",_chave);
        for (int i = 0; i < _chaveValores.length; i++) {
            Log.i("_chaveValores", _chaveValores[i]);
        }
        */
        var sucesso = false
        val atualizado: Int
        if (_table.substring(0, 3).equals("MOB", ignoreCase = true)) {
            if (openDBi()) {
                try {
                    atualizado = bdi!!.update(_table, _camposValores, _chave, _chaveValores)
                    sucesso = true
                } catch (e: SQLException) {
                    Log.e("I SqlDbMngr.updateRow ", e.toString())
                    e.printStackTrace()
                    sucesso = false
                }
                closeDBi()
            } else {
                sucesso = false
            }
        } else {
            if (openDB()) {
                try {
                    atualizado = bd!!.update(_table, _camposValores, _chave, _chaveValores)
                    sucesso = true
                } catch (e: SQLException) {
                    Log.e("E SqlDbMngr.updateRow ", e.toString())
                    e.printStackTrace()
                    sucesso = false
                }
                closeDB()
            } else {
                sucesso = false
            }
        }
        return sucesso
    }


    //INTERNAS EXCLUSIVAMENTE
    fun listaUsuarios() {
        val cursor: Cursor
        if (openDBi()) {
            cursor = bdi!!.query(
                "MOB10211",
                arrayOf("_ID", "CDLOGIN", "CDSENHA", "DSDEVICEEMAIL"),
                null,
                null,
                null,
                null,
                null
            )
            cursor.moveToFirst()
            if (!cursor.isAfterLast) { //Log.i("conexao", "count -> "+cursor.getCount());
                do { /*
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        Log.i("CAMPO", cursor.getColumnName(i));
                        Log.i("VALOR", cursor.getString(i));
                    }
                    */
                } while (cursor.moveToNext())
            }
            cursor.close()
            closeDBi()
        }
    }


    fun setFtp(_camposValores: ContentValues?): Boolean {
        val sucesso: Boolean
        var _retorno = 0
        if (openDBi()) {
            val cursor =
                bdi!!.rawQuery("select count(*) from MOBFTPCONF", null)
            cursor.moveToFirst()
            _retorno = cursor.getInt(0)
            cursor.close()
            sucesso = if (_retorno > 0) { //faz o update
                try {
                    bdi!!.update(
                        "MOBFTPCONF",
                        _camposValores,
                        " _ID = ? ",
                        arrayOf("1")
                    )
                    true
                } catch (e: SQLException) {
                    Log.e("I SqlDbMngr.updateRow ", e.toString())
                    e.printStackTrace()
                    false
                }
            } else { //faz o insert
                try {
                    if (bdi!!.insert("MOBFTPCONF", null, _camposValores) == -1L) {
                        false
                    } else {
                        true
                    }
                } catch (e: SQLException) {
                    Log.e("SqlDbMngr.setFtp ", e.toString())
                    e.printStackTrace()
                    false
                }
            }
            closeDBi()
        } else {
            sucesso = false
        }
        return sucesso
    }

    fun getFtpInit(pasta: String?, basePath: String): ContentValues {
        val _contentvalues = ContentValues()
        val cursor: Cursor
        val cursorx: Cursor
        val arq = "$basePath/IMI/iam.db3"
        if (!File(arq).exists()) {
            if (openDBi()) {
                cursor = bdi!!.query(
                    "MOBFTPCONF",
                    arrayOf("_ID", "SERVIDOR", "USUARIO", "SENHA", "PASTA"),
                    null,
                    null,
                    null,
                    null,
                    null
                )
                cursor.moveToFirst()
                if (!cursor.isAfterLast) { //Log.i("conexao", "count -> "+cursor.getCount());
                    _contentvalues.put("SERVIDOR", cursor.getString(0))
                    _contentvalues.put("USUARIO", cursor.getString(1))
                    _contentvalues.put("SENHA", cursor.getString(2))
                    _contentvalues.put("PASTA", pasta)
                    //Log.i("conexao", cursor.getString(1)+" - "+cursor.getString(2)+" - "+cursor.getString(3)+" - "+cursor.getString(4));
                }
                cursor.close()
                closeDBi()
            }
        } else {
            if (openDB()) {
                cursorx = bd!!.query(
                    "TPT987",
                    arrayOf("HOSTFTP", "USERFTP", "PASSFTP", "EMPRESA"),
                    null,
                    null,
                    null,
                    null,
                    null
                )
                cursorx.moveToFirst()
                if (!cursorx.isAfterLast) {
                    _contentvalues.put("SERVIDOR", cursorx.getString(0))
                    _contentvalues.put("USUARIO", cursorx.getString(1))
                    _contentvalues.put("SENHA", cursorx.getString(2))
                    _contentvalues.put("PASTA", cursorx.getString(3))
                }
                cursorx.close()
                closeDB()
            }
        }
        return _contentvalues
    }

    fun dropTablei(_table: String) {
        if (_table.substring(0, 3).equals("MOB", ignoreCase = true)) {
            if (openDBi()) {
                bdi!!.execSQL("DROP TABLE $_table")
                bdi!!.execSQL("VACUUM")
                closeDBi()
                //Log.i("BD","Tabela "+table+" vazia");
            }
        }
    }
    class CustomSQLiteOpenHelper(_ctx: Context?) :
        SQLiteOpenHelper(_ctx, "IAM", null, 9) {
        override fun onCreate(_bd: SQLiteDatabase) {
        }

        override fun onUpgrade(
            _bd: SQLiteDatabase,
            _vAnterior: Int,
            _vNova: Int
        ) {
        }
    }




}