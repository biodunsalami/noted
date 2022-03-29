package com.biodun.domore

import android.app.Application
import com.biodun.domore.data.NoteDatabase

class BaseApplication : Application() {

    val noteDatabase: NoteDatabase by lazy { NoteDatabase.getDatabase(this) }

}