$(".single-item").slick({
    infinite: true,
    slidesToShow: 6,
    slidesToScroll: 1,
    autoplay: false,
    autoplaySpeed: 2000
});

function novedadesClick() {
    var button_nav_n1 = document.getElementById("button_nav_n1");
    var button_nav_n2 = document.getElementById("button_nav_n2");
    button_nav_n1.innerHTML = "Inicio";
    button_nav_n2.innerHTML = "Más populares";
    button_nav_n1.setAttribute("href", "javascript:inicioClick()");
    button_nav_n2.setAttribute("href", "javascript:masPopularesClick()");

    document.getElementById("carrousel-category-n1").innerHTML = "Nuevos";
    document.getElementById("carrousel-category-n2").innerHTML = "Autores nuevos";
    document.getElementById("carrousel-category-n3").innerHTML = "Mas vendidos";

}

function masPopularesClick() {
    var button_nav_n1 = document.getElementById("button_nav_n1");
    var button_nav_n2 = document.getElementById("button_nav_n2");
    button_nav_n1.innerHTML = "Inicio";
    button_nav_n2.innerHTML = "Novedades";
    button_nav_n1.setAttribute("href", "javascript:inicioClick()");
    button_nav_n2.setAttribute("href", "javascript:novedadesClick()");

    document.getElementById("carrousel-category-n1").innerHTML = "Más vendidos esta semana";
    document.getElementById("carrousel-category-n2").innerHTML = "Más visitados esta semana";
    document.getElementById("carrousel-category-n3").innerHTML = "Mas vendidos";

}

function inicioClick() {
    var button_nav_n1 = document.getElementById("button_nav_n1");
    var button_nav_n2 = document.getElementById("button_nav_n2");
    button_nav_n1.innerHTML = "Novedades";
    button_nav_n2.innerHTML = "Más populares";
    button_nav_n1.setAttribute("href", "javascript:novedadesClick()");
    button_nav_n2.setAttribute("href", "javascript:masPopularesClick()");

    document.getElementById("carrousel-category-n1").innerHTML = "Más vendidos";
    document.getElementById("carrousel-category-n2").innerHTML = "Populares";
    document.getElementById("carrousel-category-n3").innerHTML = "Novedades";

}
