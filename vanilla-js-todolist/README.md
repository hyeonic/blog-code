# valilla-js-todolist

순수한 javascript를 활용하여 todolist를 만든다.

## toggle

HTML에서 DOM 요소의 CSS 클래스를 동적으로 추가 및 제거하기 위한 역할이다.

```js
const todoItem = document.createElement('li');
todoItem.classList.add('todo-item');
todoItem.classList.toggle('completed', todo.completed);
```

todo에 completed 여부에 따라 `completed` class를 추가 및 제거한다.

## addEventListener

addEventListener는 DOM 요소에 이벤트 리스너를 등록하는 메서드이다. `click`, `keydown`과 같은 이벤트 타입을 지정할 수 있다. 

이벤트 타입으로 지정한 이벤트가 발생하면 전달한 함수가 호출되어 이벤트 처리 로직을 수행할 수 있다.

```js
checkbox.addEventListener('change', () => toggleTodoCompletion(todo.id));
```
