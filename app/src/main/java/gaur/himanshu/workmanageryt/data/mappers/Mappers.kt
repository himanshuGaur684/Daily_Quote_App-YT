package gaur.himanshu.workmanageryt.data.mappers

import gaur.himanshu.workmanageryt.data.model.QuoteDTO
import gaur.himanshu.workmanageryt.domain.model.Quote

fun QuoteDTO.toDomain(workType: String): Quote {
    return Quote(author, id, quote, workType)
}