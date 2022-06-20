//productRelates
const getProducts = () => {
    return fetch('/api/products')
        .then(r => r.json());
}
const createProductHtmlElement = (product) => {
    const template = `
        <li>
            <h4>${product.name}</h4>
            <img src="${product.imageUrl}"/>
            <span>${product.price}</span>
            <button data-product-id="${product.id}" >Add to cart</button>
        </li>
    `;

    return createHtmlElementFromString(template);
}
const createHtmlElementFromString = (htmlTemplate) => {
    const parentEl = document.createElement('div');
    parentEl.innerHTML = htmlTemplate.trim();

    return parentEl.firstChild;
}
//salesRelated
const getCurrentOffer = () => {
    return fetch('/api/sales/current-offer')
            .then(r => r.json());
}
const refreshOffer = (cartEl, offer) => {
    cartEl.querySelector('.cart__total').textContent = `${offer.total} PLN`;
    cartEl.querySelector('.cart__itemsCount').textContent = `${offer.itemsCount}`;
}
//MAIN
(() => {
    const cartEl = document.querySelector('.cart');
    getCurrentOffer()
        .then(offer => refreshOffer(cartEl, offer));

    const productListEl = document.querySelector('.products__list');
        getProducts()
            .then(products => {
                products
                    .map(product => createProductHtmlElement(product))
                    .forEach(productHtml => {
                        productListEl.appendChild(productHtml);
                    });
            })
            .catch(e => console.log(e));
})();

