package com.example.neutrophil.game

import android.content.Context
import android.graphics.Canvas

class ItemManager {
    var numItems = 0
    @Transient var items = mutableListOf<Item>()


    fun update(){
        for (item in items) item.update()
    }

    fun draw(canvas: Canvas) {
        for (item in items) item.draw(canvas)
    }

    fun setup(context: Context){
        if(items.size == 0) {
            for (i in 0..4) {
                var item = Item(context)
                items.add(item)
                numItems++
            }
        }
        else{
            for(item in items)
            {
                item.setItem(context)
            }
        }
    }

}