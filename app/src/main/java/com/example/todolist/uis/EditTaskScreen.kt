package com.example.todolist.uis

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.model.Task
import com.example.todolist.viewModel.TaskViewModel

@Composable
fun EditTaskScreen(viewModel: TaskViewModel, task: Task, onTaskUpdate:()->Unit){

    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description) }

    Scaffold { padding->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding),
            contentAlignment = Alignment.Center){
            Column(modifier = Modifier
                .width(300.dp)
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                TextField(
                    value = title,
                    onValueChange = {title = it},
                    label = { Text("Title") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = {description = it},
                    label = { Text("Description") }
                )
                Button(onClick = {
                    viewModel.update(task.copy(title = title, description = description))
                    onTaskUpdate()
                }) {
                    Text("Update Task")

                }
            }

        }

    }

}