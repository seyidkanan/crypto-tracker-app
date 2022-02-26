package az.cryptotracker.app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import az.cryptotracker.app.R
import az.cryptotracker.app.worker.CoinPriceWatcherWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createCoinWatcherWorker()
    }

    private fun createCoinWatcherWorker() {
        val myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val coinWatcherWork =
            PeriodicWorkRequest.Builder(CoinPriceWatcherWorker::class.java, 15, TimeUnit.MINUTES)
                .setInitialDelay(15, TimeUnit.SECONDS)
                .setConstraints(myConstraints)
                .addTag("myWorkManager")
                .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "myWorkManager",
            ExistingPeriodicWorkPolicy.REPLACE, coinWatcherWork
        )
    }

}