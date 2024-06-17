setInterval(
    () => fetch('http://localhost:8080/api/products')
        .then(response => response.json())
        .then(data => console.log(data)),
    5000
);
