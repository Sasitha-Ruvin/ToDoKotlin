package com.example.todolist.uis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.example.todolist.viewModel.TaskViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.model.Task

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val tasks = viewModel.allTasks.observeAsState(initial = emptyList())
    var showAddTaskScreen by remember { mutableStateOf(false) }
    var showEditTaskScreen by remember { mutableStateOf(false) }
    var currentTask by remember { mutableStateOf<Task?>(null) }

    if (showAddTaskScreen) {
        AddTaskScreen(viewModel) {
            showAddTaskScreen = false
        }
    } else if (showEditTaskScreen && currentTask != null) {
        EditTaskScreen(viewModel, currentTask!!) {
            showEditTaskScreen = false
            currentTask = null
        }
    } else {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    showAddTaskScreen = true
                }) {
                    Text("+")
                }
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {

                Text(
                    text = "To Do List",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )


                if (tasks.value.isEmpty()) {
                    Text(
                        text = "Empty",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                } else {
                    // List of tasks
                    LazyColumn {
                        items(tasks.value) { task ->
                            TaskItem(task, onEdit = { selectedTask ->
                                currentTask = selectedTask
                                showEditTaskScreen = true
                            }, onDelete = { selectedTask ->
                                viewModel.delete(selectedTask)
                            })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, onEdit: (Task) -> Unit, onDelete: (Task) -> Unit) {
    Card(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = task.title, style = MaterialTheme.typography.titleLarge)
                Text(text = task.description, style = MaterialTheme.typography.bodyLarge)
            }
            Row {
                IconButton(onClick = { onEdit(task) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Task")
                }
                IconButton(onClick = { onDelete(task) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Task")
                }
            }
        }
    }
}
