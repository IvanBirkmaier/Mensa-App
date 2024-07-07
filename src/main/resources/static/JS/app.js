if ('serviceWorker' in navigator) {
    navigator.serviceWorker.register('../../sw.js')
        .then(function(registration) {
            registration.addEventListener('updatefound', function() {
                // If updatefound is fired, it means that there's
                // a new service worker being installed.
                var installingWorker = registration.installing;
                console.log('A new service worker is being installed:',
                    installingWorker);

                // You can listen for changes to the installing service worker's
                // state via installingWorker.onstatechange
            });
        })
        .catch(function(error) {
            console.log('Service worker registration failed:', error);
        });
} else {
    console.log('Service workers are not supported.');
}
Notification.requestPermission(function(status) {
    console.log('Notification permission status:', status);
});
function likeNotification(name) {
    if (Notification.permission == 'granted') {
        navigator.serviceWorker.getRegistration().then(function(reg) {
            reg.showNotification("Dein FAV ist gespeichert=) "+ name);
        });
    }
}
function dislikeNotification(name) {
    if (Notification.permission == 'granted') {
        navigator.serviceWorker.getRegistration().then(function(reg) {
            reg.showNotification(name +" wurde aus deinen FAVS entfernt =(");
        });
    }
}
//Klassenvariablen
let liked;
//Daten Kanteenendaten aus dem Backend holen
async function createTable() {
    //Alle Mensen
    const response = await fetch('/canteensjsonlist');
   const canteens = await response.json();
    tabele(canteens);
    initMap(canteens);
}
//Erstellt mit KantinenJSON  eine Tabellarische übersicht der Kantinen
function tabele(data){
    let tablecanteens = data;
    const div = document.getElementById("divTable");
    const tab = document.getElementById("mensatabelle");
    const th = document.createElement('thead');
    const tb = document.createElement('tbody');
    const thr = th.insertRow();
    const th1 = thr.insertCell();
    const th2 = thr.insertCell();
    const th3 = thr.insertCell();
    const th4 = thr.insertCell()
    th1.appendChild(document.createTextNode("Name"));
    th2.appendChild(document.createTextNode("Stadt"));
    th3.appendChild(document.createTextNode("Adresse"));
    th4.appendChild(document.createTextNode("Like"));
    tab.appendChild(th);
        tablecanteens.forEach(element => {
            const tr = tb.insertRow();
            const infoLink = document.createElement("a");
            infoLink.setAttribute('href',
                "#");
            infoLink.onclick = function () {
               // like(element.id, tablecanteens),
                serachCanteen(element.id, tablecanteens);
                    likeNotification(element.name)
                like();
            };
            const linText = document.createTextNode("Like");
            infoLink.appendChild(linText);
            const td = tr.insertCell();
            const td2 = tr.insertCell();
            const td3 = tr.insertCell();
            const td4 = tr.insertCell();
            td.appendChild(document.createTextNode(element.name));
            td2.appendChild(document.createTextNode(element.city));
            td3.appendChild(document.createTextNode(element.adresse));
            td4.appendChild(infoLink);
            tb.appendChild(tr);
        })
    tab.appendChild(tb);
    div.appendChild(tab)
};
//funktion itariert durch alle Canteenen und gibt eine bestimmte zurück
function serachCanteen(id, canteens){
    canteens.forEach(element => {
        if (id === element.id) {
                liked = element;
        }
    })
}
//Implementiert eine Likefunktion und erstellt eine Tabelle für geliked Kantinnen
function like() {
    const div = document.getElementById("likedMensa");
    const tab = document.getElementById("liketable");
    const tb = document.createElement('tbody');
            const tr = tb.insertRow();
            tr.setAttribute("id", String(liked.id))
            const unlike = document.createElement("a");
            unlike.setAttribute('href',
                `#`);
            unlike.onclick = function () {
                unlikefunction(liked.id),
                    dislikeNotification(liked.name)
            };
    const linText = document.createTextNode("Unlike");
    unlike.appendChild(linText);
    const td = tr.insertCell();
    const td2 = tr.insertCell();
    const td3 = tr.insertCell();
    const td4 = tr.insertCell();
    td.appendChild(document.createTextNode(liked.name));
    td2.appendChild(document.createTextNode(liked.city));
    td3.appendChild(document.createTextNode(liked.adresse));
    td4.appendChild(unlike);
    tb.appendChild(tr);
    tab.appendChild(tb);
    div.appendChild(tab)
};

//Löscht den gelikeden Kantineneintrag durch eine übergebene ID
function unlikefunction(id) {
    let row = document.getElementById(id);
    row.parentNode.appendChild(document.createElement('tr'))
   row.parentNode.removeChild(row)
}

//Erstellt eine Map mit spezifischen Markern für alle Mensen
 function initMap(data) {
    const canteensList = data;
    const map = new google.maps.Map(
        document.getElementById("map"),
            {
            zoom: 4,
            center: {lat: 52.5200066,lng: 13.404954},
        }
    );
    canteensList.forEach(element =>{
        const p = { lat: element.nearLAT, lng: element.nearLNG};
        const infoWindow = new google.maps.InfoWindow();
        const marker =  new google.maps.Marker({
            position: p ,
            map: map,
            title: `${element.name} `+`<a onclick="uebersicht(${element.id})" href="#">Übersicht</a>`,
            optimized: false,
        });
        marker.addListener("click", () => {
            infoWindow.close();
            infoWindow.setContent(marker.getTitle());
            infoWindow.open(marker.getMap(), marker);
            map.setZoom(11);
            map.setCenter(marker.getPosition());
        });
    });
}
//Erstellt eine Übersicht der Mensagerichte Öffnungsstatus etc auf basis einer MensenID die getriggert wird dürch den Übersichtsachor des Markers
async function uebersicht(id){
    const response = await fetch('/mensa/'+id);
    const uebersichtEntity = await response.json();
    const divUebersicht = document.getElementById("divUebersicht");
    const tabelle = document.createElement('table');
    let header = document.getElementById("headerMensaInfo");
    let open = document.getElementById("mensaOpen");
try {
    if(uebersichtEntity.offenJaNein === true ){
        header.innerHTML = uebersichtEntity.name;
        open.innerHTML = "Derzeit "+uebersichtEntity.offenJaNein;
    }else{
        header.innerHTML = uebersichtEntity.name;
        open.innerHTML = "Derzeit "+uebersichtEntity.offenJaNein;
        const meals = uebersichtEntity.gerichte;
        //tabellenelemente
        const thead = document.createElement('thead');
        const tbody =document.createElement('tbody');
        //thead
        const thr= thead.insertRow();
        const th1 =  thr.insertCell();
        const th2 =  thr.insertCell();
        const th3 =  thr.insertCell();
        const th4 =  thr.insertCell();
        th1.appendChild(document.createTextNode("Name"));
        th2.appendChild(document.createTextNode("Kategorie"));
        th3.appendChild(document.createTextNode("Notizen"));
        th4.appendChild(document.createTextNode("Preis"));
        thead.appendChild(thr)
        const tr = tbody.insertRow();
        const td = tr.insertCell();
        const td2= tr.insertCell();
        const td3= tr.insertCell();
        const td4 = tr.insertCell();
        //Tabelle wird mit gerichten gefüllt
        meals.forEach( element => {
            //Tbody
            const tr = tbody.insertRow();
            const td = tr.insertCell();
            const td2= tr.insertCell();
            const td3= tr.insertCell();
            const td4 = tr.insertCell();
            //Objektelemente
            const mealName = element.name;
            const catego = element.category;
            const notes = element.notes;
            const studentPrice = element.prices.student;
            const emplyee = element.prices.employees;
            const others = element.prices.others;
            const pupil =element.prices.pupils;
            td.appendChild(document.createTextNode(mealName));
            td2.appendChild(document.createTextNode(catego));
            td3.appendChild(document.createTextNode(notes));
            td4.appendChild(document.createTextNode("Student: " + studentPrice+" e\n"));
            td4.appendChild(document.createTextNode("Employee "+ emplyee+" €\n"));
            td4.appendChild(document.createTextNode("Others " + others+" €\n"));
            td4.appendChild(document.createTextNode("Pupils "+pupil+" €"));
        })
        tabelle.appendChild(thead);
        tabelle.appendChild(tbody);
        divUebersicht.appendChild(tabelle)
    }
}catch (e) {
        header.innerHTML ="Es tut uns leid.."
        open.innerHTML ="...Über die ausgewählte Mensa liegen uns derzeit leider keine ausführlichen Informationen vor";
    }
    divUebersicht.appendChild(header);
    divUebersicht.appendChild(open);

}



