function main(id,login,key){
    environnement = new Object();
    environnement.users = [] ;
    environnement.actif = false;
    environnement.key ="";
    environnement.userId="";
    environnement.recherche="";

    if((id!== undefined)&&(login!== undefined)&&(key!== undefined)){
        environnement.actif=true;
    	environnement.key = key;
        environnement.users[id]=new User(id, login);
        environnement.userId=id;
    }
    
    updateDivConnection();
    rechercheGlobale();   
}
