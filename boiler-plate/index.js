const express = require('express')
const app = express()
const port = 3000

const mongoose = require('mongoose');
mongoose.connect('mongodb://hyeoni.c:hyeoni.c@localhost:27017/boiler-plate?authMechanism=DEFAULT&authSource=admin')
    .then(() => console.log('MongoDB connected'))
    .catch(err => console.log(err));

app.get('/', (req, res) => {
    res.send('Hello World!')
})

const { User } = require("./models/User")
app.post('/resist', (req, res) => {
    const user = new User
})

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})