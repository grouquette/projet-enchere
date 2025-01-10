document.addEventListener("DOMContentLoaded", function () {
    console.log("Le script est chargé et le DOM est prêt !");
    
    // Sélectionner les checkboxes
    const achatsCheckbox = document.querySelector("input[name='achats']");
    const mesVentesCheckbox = document.querySelector("input[name='mesVentes']");
    const autresCheckboxes = document.querySelectorAll(".checkbox-square");

    // Vérification que les éléments existent avant d'ajouter des événements
    if (achatsCheckbox && mesVentesCheckbox) {
        achatsCheckbox.addEventListener("change", toggleCheckboxes);
        mesVentesCheckbox.addEventListener("change", toggleCheckboxes);
    }

    // Appel initial pour activer ou désactiver les cases à cocher
    toggleCheckboxes();

    // Fonction pour activer/désactiver les cases à cocher
    function toggleCheckboxes() {
        console.log("toggleCheckboxes appelé");

        // Vérifier si l'élément "achatsCheckbox" existe avant d'y accéder
        if (achatsCheckbox) {
            if (achatsCheckbox.checked) {
                console.log("Achats est coché, désactiver Lister mes ventes et les autres cases");
                mesVentesCheckbox.disabled = true;
                autresCheckboxes.forEach(function(checkbox) {
                    checkbox.disabled = true;
                });
            } else {
                console.log("Achats n'est pas coché, réactiver Lister mes ventes et les autres cases");
                mesVentesCheckbox.disabled = false;
                autresCheckboxes.forEach(function(checkbox) {
                    checkbox.disabled = false;
                });
            }
        }

        // Vérifier si l'élément "mesVentesCheckbox" existe avant d'y accéder
        if (mesVentesCheckbox) {
            if (mesVentesCheckbox.checked) {
                console.log("Lister mes ventes est coché, désactiver Achats et les cases d'enchères");
                achatsCheckbox.disabled = true;
                document.querySelector("input[name='encheresOuvertes']").disabled = true;
                document.querySelector("input[name='mesEncheresEnCours']").disabled = true;
                document.querySelector("input[name='mesEncheresRemportees']").disabled = true;
            } else {
                console.log("Lister mes ventes n'est pas coché, réactiver Achats et les cases d'enchères");
                achatsCheckbox.disabled = false;
                document.querySelector("input[name='encheresOuvertes']").disabled = false;
                document.querySelector("input[name='mesEncheresEnCours']").disabled = false;
                document.querySelector("input[name='mesEncheresRemportees']").disabled = false;
            }
        }
    }
});
