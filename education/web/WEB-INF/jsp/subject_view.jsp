<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
    <head>
        <!-- Кодировка веб-страницы -->
        <meta charset="utf-8">
        <!-- Настройка viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Подключаем Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
    <h2>Регистрация</h2>
    <form class="container">
        <!-- name -->
        <div class="form-group">
            <label class="control-label col-xs-3" for="lastName">Subject name:</label>
            <div class="col-xs-5">
                <input type="text" class="form-control" id="lastName" placeholder="Введите фамилию">
            </div>
        </div>
        <!-- description -->
        <div class="form-group">
            <label class="control-label col-xs-3" for="firstName">Description</label>
            <div class="col-xs-9">
                <input type="text" class="form-control" id="firstName" placeholder="Введите имя">
            </div>
        </div>
        <!-- valid -->
        <div class="form-group">
            <label class="control-label col-xs-3">Valid:</label>
            <div class="col-xs-2">
                <label class="radio-inline">
                    <input type="radio" name="genderRadios" value="active"> Active
                </label>
            </div>
            <div class="col-xs-2">
                <label class="radio-inline">
                    <input type="radio" name="genderRadios" value="deprecated"> Deprecated
                </label>
            </div>
        </div>
        <!-- date_of_creation -->
        <div class="form-group">
            <label class="control-label col-xs-3">Дата рождения:</label>
            <div class="col-xs-3">
                <select class="form-control">
                    <option>Дата</option>
                </select>
            </div>
            <div class="col-xs-3">
                <select class="form-control">
                    <option>Месяц</option>
                </select>
            </div>
            <div class="col-3">
                <select class="form-control">
                    <option></option>
                </select>
            </div>
        </div>
        <br />
        <!-- button -->
        <div class="form-group">
            <div class="col-xs-offset-3 col-xs-9">
                <input type="submit" class="btn btn-primary" value="Регистрация">
                <input type="reset" class="btn btn-default" value="Очистить форму">
            </div>
        </div>
    </form>

    <!-- Подключаем jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <!-- Подключаем плагин Popper -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous">
    </script>
    <!-- Подключаем Bootstrap JS -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous">
    </script>
    </body>
</html>
