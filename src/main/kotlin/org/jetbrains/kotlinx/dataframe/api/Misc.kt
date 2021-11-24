package org.jetbrains.kotlinx.dataframe.api

import org.jetbrains.kotlinx.dataframe.DataColumn
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.DataRow
import org.jetbrains.kotlinx.dataframe.dataFrameOf
import org.jetbrains.kotlinx.dataframe.impl.owner
import org.jetbrains.kotlinx.dataframe.io.renderToString
import org.jetbrains.kotlinx.dataframe.schema.DataFrameSchema
import org.jetbrains.kotlinx.dataframe.values

// region copy

public fun <T> DataFrame<T>.copy(): DataFrame<T> = columns().toDataFrame().cast()

// endregion

// region transpose

public fun <T> DataRow<T>.transpose(): DataFrame<*> =
    dataFrameOf(owner.columnNames().toValueColumn("key"), values.toColumn(name = "value", Infer.Type))

// endregion

// region print

public fun <T> DataFrame<T>.print(
    rowsLimit: Int = 20,
    valueLimit: Int = 40,
    borders: Boolean = false,
    alignLeft: Boolean = false,
    columnTypes: Boolean = false,
    title: Boolean = false
): Unit = println(renderToString(rowsLimit, valueLimit, borders, alignLeft, columnTypes, title))

public fun DataFrameSchema.print(): Unit = println(this)
public fun <T> DataRow<T>.print(): Unit = println(this)
public fun <T> DataColumn<T>.print(): Unit = println(this)
public fun <T, G> GroupBy<T, G>.print(): Unit = println(this)

// endregion
