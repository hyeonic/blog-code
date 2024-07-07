const todoInput = document.getElementById('todo-input');
const todoAddButton = document.getElementById('todo-add-button');
const todolist = document.getElementById('todolist');

let initId = Date.now();
let todos = new Map();

function addTodo() {
    const todoText = todoInput.value.trim();
    if (todoText !== '') {
        let id = Date.now();
        const newTodo = {
            id: id,
            text: todoText,
            completed: false
        };
        todos.set(id, newTodo);
        renderTodos();
        todoInput.value = '';
    }
}

function renderTodos() {
    todolist.innerHTML = '';
    todos.forEach(todo => {
        const todoItem = document.createElement('li');
        todoItem.classList.add('todo-item');
        todoItem.classList.toggle('completed', todo.completed);

        const checkbox = document.createElement('input');
        checkbox.type = 'checkbox';
        checkbox.checked = todo.completed;
        checkbox.classList.add('todo-checkbox');
        checkbox.addEventListener('change', () => toggleTodoCompletion(todo.id));

        const span = document.createElement('span');
        span.textContent = todo.text;

        const deleteButton = document.createElement('button');
        deleteButton.classList.add('delete-button');
        deleteButton.textContent = '삭제';
        deleteButton.addEventListener('click', () => deleteTodo(todo.id));

        todoItem.appendChild(checkbox);
        todoItem.appendChild(span);
        todoItem.appendChild(deleteButton);
        todolist.appendChild(todoItem);
    });
}

function toggleTodoCompletion(id) {
    let targetTodo = todos.get(id);
    todos.set(id, toggle(targetTodo))
    renderTodos();
}

function toggle(todo) {
    return {
        id: todo.id,
        text: todo.text,
        completed: !todo.completed
    }
}

function deleteTodo(id) {
    todos.delete(id);
    renderTodos();
}

todoAddButton.addEventListener('click', addTodo);
todoInput.addEventListener('keyup', e => {
    if (e.key === 'Enter') {
        addTodo();
    }
});

renderTodos();