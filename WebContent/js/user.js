// Constructeur
function User (id, login, contact){
    this.id = id ;
    this.login = login ;
    this.contact = (contact === undefined) ? false : contact;
}

// Comprend pas trop l'utilit�
//-> relance de la  fonction recherche qui met � jour les donn�es automatiquement
// pour une MAJ automatique peut-�tre ?
// Methode associee au constructeur : prototype
User.prototype.modifierStatus = function(){
    this.contact = ! this.contact ;
    // ajout par la suite de la connnexion avec le serveur

}


