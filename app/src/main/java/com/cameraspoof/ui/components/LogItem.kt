package com.cameraspoof.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cameraspoof.domain.model.LogEntry
import com.cameraspoof.domain.model.LogLevel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun LogItem(log: LogEntry) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(60.dp)
                    .background(
                        color = when (log.level) {
                            LogLevel.DEBUG -> Color(0xFF2196F3)
                            LogLevel.INFO -> Color(0xFF4CAF50)
                            LogLevel.WARNING -> Color(0xFFFF9800)
                            LogLevel.ERROR -> Color(0xFFF44336)
                        },
                        shape = RoundedCornerShape(2.dp)
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = log.level.name,
                        style = MaterialTheme.typography.labelMedium,
                        color = when (log.level) {
                            LogLevel.DEBUG -> Color(0xFF2196F3)
                            LogLevel.INFO -> Color(0xFF4CAF50)
                            LogLevel.WARNING -> Color(0xFFFF9800)
                            LogLevel.ERROR -> Color(0xFFF44336)
                        }
                    )

                    Text(
                        text = dateFormat.format(Date(log.timestamp)),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = log.packageName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = log.message,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
