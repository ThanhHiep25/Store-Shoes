function addToCart(productId) {
    // JavaScript to add a product to the cart
    console.log("Product " + productId + " added to cart.");
}

function removeFromCart(productId) {
    // JavaScript to remove a product from the cart
    console.log("Product " + productId + " removed from cart.");
}

document.addEventListener('DOMContentLoaded', function() {
    // JavaScript for handling cart quantity changes
    const quantityInputs = document.querySelectorAll('.quantity-input');
    quantityInputs.forEach(input => {
        input.addEventListener('change', function() {
            const productId = this.getAttribute('data-id');
            const quantity = this.value;
            console.log("Updated quantity for product " + productId + ": " + quantity);
        });
    });
});
