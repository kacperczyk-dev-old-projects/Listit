package com.dawid.listit.util

import kotlin.collections.Map.Entry

fun <K, V> createFifoMap(maxEntries: Int): MutableMap<K, V> {
    return object : LinkedHashMap<K, V>(maxEntries * 10 / 7, 0.7f, true) {
        override fun removeEldestEntry(eldest: Entry<K, V>): Boolean {
            return size > maxEntries
        }
    }
}