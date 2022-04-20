import com.fastcontech.weatherapp.data.network.NetworkConstants
import com.fastcontech.weatherapp.data.network.NetworkConstants.TIME_OUT
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

object NetworkClient {
    private val json = Json {
        prettyPrint = true
        isLenient = true
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    val httpClient = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("${NetworkConstants.MESSAGE} $message")
                }
            }
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            socketTimeoutMillis = TIME_OUT
            requestTimeoutMillis = TIME_OUT
            connectTimeoutMillis = TIME_OUT
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    val citiesWeatherHttp = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("${NetworkConstants.MESSAGE} $message")
                }
            }
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            socketTimeoutMillis = TIME_OUT
            requestTimeoutMillis = TIME_OUT
            connectTimeoutMillis = TIME_OUT
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    val favouriteHttp = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("${NetworkConstants.MESSAGE} $message")
                }
            }
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            socketTimeoutMillis = TIME_OUT
            requestTimeoutMillis = TIME_OUT
            connectTimeoutMillis = TIME_OUT
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}

