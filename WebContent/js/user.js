// Constructeur
function User (id, login, contact){
    this.id = id ;
    this.login = login ;
    this.contact = (contact === undefined) ? false : contact;
}

// Comprend pas trop l'utilité
//-> relance de la  fonction recherche qui met à jour les données automatiquement
// pour une MAJ automatique peut-être ?
// Methode associee au constructeur : prototype
User.prototype.modifierStatus = function(){
    this.contact = ! this.contact ;
    // ajout par la suite de la connnexion avec le serveur

}


