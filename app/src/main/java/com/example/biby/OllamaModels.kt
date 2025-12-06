data class OllamaRequest(
    val model: String,
    val prompt: String
)

data class OllamaResponse(
    val model: String? = null,
    val response: String? = null,
    val done: Boolean? = null
)
