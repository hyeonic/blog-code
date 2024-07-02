const todoInput = document.getElementById('todo-input');
const todoAddButton = document.getElementById('todo-add-button');
const todolist = document.getElementById('todolist');

let todos = [
    {
        id: Date.now(),
        text: "todolist 만들기",
        completed: false
    }
];

function addTodo() {
    const todoText = todoInput.value.trim();
    if (todoText !== '') {
        const newTodo = {
            id: Date.now(),
            text: todoText,
            completed: false
        };
        todos.push(newTodo);
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
    todos = todos.map(todo => {
        if (todo.id === id) {
            return {
                id: todo.id,
                text: todo.text,
                completed: !todo.completed
            };
        }
        return todo;
    });

    renderTodos();
}

function deleteTodo(id) {
    todos = todos.filter(todo => todo.id !== id);
    renderTodos();
}

todoAddButton.addEventListener('click', addTodo);
todoInput.addEventListener('keyup', e => {
    if (e.key === 'Enter') {
        addTodo();
    }
});

renderTodos();