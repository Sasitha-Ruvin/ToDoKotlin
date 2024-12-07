package com.example.todolist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.TaskRepository
import com.example.todolist.model.Task
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository):ViewModel() {

    val allTasks:LiveData<List<Task>> = repository.allTasks

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }
    fun update(task: Task)=viewModelScope.launch {
        repository.update(task)
    }
    fun delete(task: Task)=viewModelScope.launch {
        repository.delete(task)

    }
}