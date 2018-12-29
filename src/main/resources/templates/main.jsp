<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="FMI - project">
    <meta name="author" content="Lilyana - 61887, Eva - 61940">

    <title>Математика за матура</title>

    <link href="../public/css/bootstrap.min.css" rel="stylesheet">
    <link href="../public/css/main.css" rel="stylesheet">
</head>

<body>

<header>

    <nav class="navbar navbar-expand-lg navbar-light fixed-top bg-light">

        <a class="navbar-brand" href="./index.html">Математика за матура</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">

            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="./index.html">Начало</a>
                </li>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="./main.html">Дялове на математиката<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled">Задачи без решения</a>
                </li>
            </ul>

            <form class="form-inline my-2 my-lg-0">
                <a class="nav-link" href="#">Потребителско име</a>
                <a class="nav-link" href="./loginForm.html"><img src="../public/images/loginIcon.png" width="30"
                                                                 height="30"/></a>
            </form>

        </div>

    </nav>

</header>

<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <c:forEach var="partValue" items="${partOptions}">
        <a class="dropdown-item">${partValue}</a>
    </c:forEach>
</div>

<main id="main" role="main">

    <div class="carousel-item active" id="myCarousel">
        <img src="../public/images/indexImage2.jpg">
        <div class="container">
            <div class="carousel-caption text-left">
                <h1>Добре дошли! Време е за математика!</h1>
            </div>
        </div>
    </div>

    <div id="container-subjects">

        <div id="subj">

            <ul class="nav nav-tabs">
                <c:forEach var="difficultyValue" items="${difficultyOptions}">
                    <li><a class="taskType" data-toggle="tab">${difficultyValue}</a></li>
                </c:forEach>
            </ul>

        </div>

        <div id="tasksHardness" class="tab-content">
            <c:forEach var="difficultyValue" items="${difficultyOptions}">
                <div id="${difficultyValue}" class="tab-pane">
                    <c:forEach var="task" items="${tasks}">
                        <div class="thumbnail">
                            <h3 value="${task.title}"></h3>
                            <div class="text-bottom text-right"><a href="./trainingTask.html" role="button"
                                                                   class="btn btn-primary">Решаване<i
                                    class="fa fa-arrow-right"
                                    aria-hidden="true"></i></a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:forEach>
        </div>

    </div>

    <div class="container marketing">

        <div class="row">

            <div class="col-lg-4">
                <img class="rounded-circle" src="../public/images/indexImage1.jpg" alt="Generic placeholder image"
                     width="140"
                     height="140">
                <h2>ДЗИ 2 - Математика</h2>
                <p>Искаш да се подготвиш добре по математика за матурата? Регистрирай се сега и започни да решаваш
                    задачи!</p>
                <p><a class="btn btn-secondary" href="./registrationForm.html" role="button">Детайли &raquo;</a></p>
            </div>

            <div class="col-lg-4">
                <img class="rounded-circle" src="../public/images/indexImage2.jpg" alt="Generic placeholder image"
                     width="140"
                     height="140">
                <h2>Първи стъпки</h2>
                <p>Най-лесният и удобен начин за подготовка е чрез систематично минаване през материала. Именно тук
                    можеш да откриеш задачите подредени по дялове и трудност!</p>
                <p><a class="btn btn-secondary" href="./index.html" role="button">Детайли &raquo;</a></p>
            </div>

            <div class="col-lg-4">
                <img class="rounded-circle" src="../public/images/indexImage3.jpg" alt="Generic placeholder image"
                     width="140"
                     height="140">
                <h2>Главоблъсканици</h2>
                <p>Секцията "Задачи без решения" предоставя възможност на регистрираните потребители да предизвикват
                    себе си с малко по интересни задачи по материала.</p>
                <p><a class="btn btn-secondary" href="./loginForm.html" role="button">Детайли &raquo;</a></p>
            </div>

        </div>

    </div>

    <footer class="container">
        <p class="footerHeading">&copy; 2018-2019 ELLE</p>
    </footer>

    <script src="https://code.jquery.com/jquery-2.1.1.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../../../assets/js/vendor/popper.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="../../../../assets/js/vendor/holder.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="./js/main.js"></script>

</main>
</body>
</html>