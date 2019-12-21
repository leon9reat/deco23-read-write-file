package com.medialink.deco23readwritefile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var path: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_new.setOnClickListener(this)
        btn_open.setOnClickListener(this)
        btn_save.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_new -> {
                edt_title.text?.clear()
                edt_file.text?.clear()
                Toast.makeText(this, "clearing text", Toast.LENGTH_SHORT).show()
            }
            R.id.btn_open -> showList()
            R.id.btn_save -> saveFile()
        }
    }

    private fun saveFile() {
        when {
            edt_title.text.toString().isEmpty() -> Toast.makeText(this, "title harus diisi terlebih dahulu", Toast.LENGTH_SHORT).show()
            edt_file.text.toString().isEmpty() -> Toast.makeText(this, "konten harus diisi terlebih dahulu", Toast.LENGTH_SHORT).show()
            else -> {
                val title = edt_title.text.toString()
                val text = edt_file.text.toString()
                val fileModel = FileModel()
                fileModel.filename = title
                fileModel.data = text
                FileHelper.writeToFile(fileModel, this)
                Toast.makeText(this, "saving ${fileModel.filename} file", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showList() {
        val arrayList = ArrayList<String>()
        val path: File = filesDir
        Collections.addAll(arrayList, *path.list() as Array<String>)
        val items = arrayList.toTypedArray<CharSequence>()

        val builder = AlertDialog.Builder(this)
        builder.setTitle("pilih file yang diinginkan")
        builder.setItems(items) {dialog, which ->
            loadItem(items[which].toString())
        }
        val alert = builder.create()
        alert.show()

    }

    private fun loadItem(title: String) {
        val fileModel = FileHelper.readFromFile(this, title)
        edt_title.setText(fileModel.filename)
        edt_file.setText(fileModel.data)
        Toast.makeText(this, "loading: ${fileModel.filename} data ", Toast.LENGTH_SHORT).show()
    }
}
