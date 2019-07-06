package com.company.commonlibrary.retrofit


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter


import java.io.IOException
import java.lang.reflect.Type

/**
 * @author dinglaihong
 * @email 18279727279@163.com
 * @date 2019/7/6
 * @des
 */

object GsonFactory {

    fun getGson(): Gson {
        val gson: Gson by lazy {
            newGson()
        }
        return gson
    }

    private fun newGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .registerTypeAdapter(Int::class.java, IntegerDefault0Adapter())
                .registerTypeAdapter(Int::class.javaPrimitiveType, IntegerDefault0Adapter())
                .registerTypeAdapter(Double::class.java, DoubleDefault0Adapter())
                .registerTypeAdapter(Double::class.javaPrimitiveType, DoubleDefault0Adapter())
                .registerTypeAdapter(Long::class.java, LongDefault0Adapter())
                .registerTypeAdapter(Long::class.javaPrimitiveType, LongDefault0Adapter())
                .registerTypeAdapterFactory(SafeTypeAdapterFactory())
                .create()
    }

    class DoubleDefault0Adapter : JsonSerializer<Double>, JsonDeserializer<Double> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Double? {
            try {
                //定义为long类型,如果后台返回""或者null,则返回0
                if ("" == json.asString || "null" == json.asString) {
                    return 0.00
                }
            } catch (ignore: Exception) {
            }

            try {
                return json.asDouble
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }

        override fun serialize(src: Double?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src!!)
        }
    }

    class IntegerDefault0Adapter : JsonSerializer<Int>, JsonDeserializer<Int> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Int? {
            try {
                //定义为long类型,如果后台返回""或者null,则返回0
                if ("" == json.asString || "null" == json.asString) {
                    return 0
                }
            } catch (ignore: Exception) {
            }

            try {
                return json.asInt
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }

        override fun serialize(src: Int?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src!!)
        }
    }

    class LongDefault0Adapter : JsonSerializer<Long>, JsonDeserializer<Long> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Long? {
            try {
                //定义为long类型,如果后台返回""或者null,则返回0
                if ("" == json.asString || "null" == json.asString) {
                    return 0L
                }
            } catch (ignore: Exception) {
            }

            try {
                return json.asLong
            } catch (e: NumberFormatException) {
                throw JsonSyntaxException(e)
            }

        }

        override fun serialize(src: Long?, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
            return JsonPrimitive(src!!)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class SafeTypeAdapterFactory : TypeAdapterFactory {

        override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {
            val delegate = gson.getDelegateAdapter(this, type)
            return object : TypeAdapter<T>() {
                @Throws(IOException::class)
                override fun write(out: JsonWriter, value: T) {
                    try {
                        delegate.write(out, value)
                    } catch (e: Exception) {
                        delegate.write(out, null)
                    }

                }

                @Throws(IOException::class)
                override fun read(`in`: JsonReader): T? {
                    try {
                        return delegate.read(`in`) as T
                    } catch (e: IOException) {
                        `in`.skipValue()
                    } catch (e: IllegalStateException) {
                        `in`.skipValue()
                    } catch (e: JsonSyntaxException) {
                        `in`.skipValue()
                        if (type.type is Class<*>) {
                            try {
                                return (type.type as Class<*>).newInstance() as T
                            } catch (ex: Exception) {
                                ex.printStackTrace()
                            }

                        }
                    }

                    return null
                }
            }
        }
    }
}
