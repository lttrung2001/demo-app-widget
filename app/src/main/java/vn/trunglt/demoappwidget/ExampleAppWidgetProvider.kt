package vn.trunglt.demoappwidget

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.annotation.RequiresApi

class ExampleAppWidgetProvider : AppWidgetProvider() {
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    @SuppressLint("RemoteViewLayout")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.wtf("TRUNGLE", "onUpdate")
        appWidgetIds?.forEach { appWidgetId ->
            val pendingIntent: PendingIntent = PendingIntent.getActivity(
                /* context = */ context,
                /* requestCode = */  0,
                /* intent = */ Intent(context, SecondActivity::class.java),
                /* flags = */ PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val views: RemoteViews = RemoteViews(
                context?.packageName,
                R.layout.layout_demo_app_widget
            ).apply {
                setOnClickPendingIntent(R.id.btn_open, pendingIntent)
                setTextViewText(R.id.tv_time_millis, System.currentTimeMillis().toString())
                setOnCheckedChangeResponse(R.id.cb_change_image_visibility, RemoteViews.RemoteResponse.fromPendingIntent(PendingIntent.getActivity(
                    /* context = */ context,
                    /* requestCode = */  0,
                    /* intent = */ Intent(context, MainActivity::class.java),
                    /* flags = */ PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )))
            }
            appWidgetManager?.updateAppWidget(appWidgetId, views)
        }
    }
}