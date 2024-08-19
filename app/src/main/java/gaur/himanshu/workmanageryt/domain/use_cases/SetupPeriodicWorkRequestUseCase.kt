package gaur.himanshu.workmanageryt.domain.use_cases

import gaur.himanshu.workmanageryt.domain.repository.QuoteRepository
import javax.inject.Inject

class SetupPeriodicWorkRequestUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {
    operator fun invoke() = quoteRepository.setupPeriodicWorkRequest()
}