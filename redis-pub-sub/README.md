# redis-pub-sub

데이터에 대한 변경사항을 반영하기 위한 폴링 구조와 메시징 시스템을 비교하여 장단점을 분석한다.

```shell
$ cd ./docker 
$ docker-compose up
```

```shell
$ redis-cli -h localhost -p 6379

localhost:6379> SUBCRIBE product_changed_event_channel
```
