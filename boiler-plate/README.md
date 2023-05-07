# boiler-plate

변형이 거의 또는 전혀 없이 여러 위치에서 반복되는 코드 섹션을 말한다.

## 초기 설정

### express.js 추가

```
npm init
```

```
npm install express --save
```

`--save`를 통해 `package.json`에 의존성 추가

```json
{
  "name": "boiler-plate",
  "version": "1.0.0",
  "description": "",
  "main": "index.js",
  "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
  "author": "hyeoni.c",
  "license": "ISC",
  "dependencies": {
    "express": "^4.18.2"
  }
}
```

`node-modules`에 다운로드된 `dependencies`가 담겨있다.   

### mongo 설치

```
docker pull mongo
```

#### docker-compose.yml
```
version: "3.9"
services:
  db:
    image: mongo:latest
    container_name: boiler-plate
    restart: always
    environment:
      MONGO_INITDB_ROOT_USERNAME: hyeoni.c
      MONGO_INITDB_ROOT_PASSWORD: hyeoni.c
    ports:
      - "27017:27017"
```

```
docker-compose up
```

### mongoose 추가

```
npm install mongoose --save 
```

### mongoose 연결



## References.

[기초 노드 리액트 강의](https://www.youtube.com/watch?v=fgoMqmNKE18&list=PL9a7QRYt5fqkZC9jc7jntD1WuAogjo_9T)
