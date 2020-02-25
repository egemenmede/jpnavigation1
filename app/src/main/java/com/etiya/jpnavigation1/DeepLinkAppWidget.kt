package com.etiya.jpnavigation1

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.os.Bundle
import android.widget.RemoteViews
import androidx.navigation.NavDeepLinkBuilder

class DeepLinkAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        val remoteViews = RemoteViews(
            context?.packageName,
            R.layout.deep_link_appwidget
        )

        val args = Bundle()
        args.putString("nameField", "Widget'tan gelen veri")

        val pendingIntent = NavDeepLinkBuilder(context!!)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.blankFragment4)
                .setArguments(args)
                .createPendingIntent()

        remoteViews.setOnClickPendingIntent(R.id.btnDeepLinkButton, pendingIntent)

        appWidgetManager?.updateAppWidget(appWidgetIds, remoteViews)
    }
}