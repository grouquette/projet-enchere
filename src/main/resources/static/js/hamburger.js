// Sélectionner les éléments nécessaires
const hamburgerButton = document.querySelector('.hamburger');
const menu = document.querySelector('.menu');

// Ajouter un gestionnaire d'événement pour le bouton hamburger
hamburgerButton.addEventListener('click', () => {
    menu.classList.toggle('active');
});
