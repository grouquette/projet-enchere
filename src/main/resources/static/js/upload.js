/**
 * 
 */

 document.addEventListener("DOMContentLoaded", () => {
    const uploadButton = document.getElementById("trigger-upload");
    const fileInput = document.getElementById("image-upload");
    const fileNameDisplay = document.getElementById("file-name");

    // Quand on clique sur le bouton, on déclenche le clic sur l'input file
    uploadButton.addEventListener("click", () => {
        fileInput.click();
    });

    // Afficher le nom du fichier sélectionné
    fileInput.addEventListener("change", () => {
        if (fileInput.files.length > 0) {
            fileNameDisplay.textContent = `Fichier sélectionné : ${fileInput.files[0].name}`;
        } else {
            fileNameDisplay.textContent = "Aucun fichier sélectionné";
        }
    });
});
