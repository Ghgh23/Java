# Импортируем необходимые классы и модули из Kivy
from kivy.app import App                  # Основной класс приложения
from kivy.uix.screenmanager import ScreenManager, Screen  # Для управления экранами
from kivy.uix.button import Button        # Для создания кнопок
from kivy.uix.boxlayout import BoxLayout  # Для создания контейнеров
from kivy.uix.label import Label         # Для создания текстовых меток
from kivy.uix.textinput import TextInput  # Для создания полей ввода
from kivy.clock import Clock             # Для работы с таймером
from random import randint, shuffle      # Для генерации случайных чисел
from kivy.core.window import Window     # Для управления окном
from kivy.uix.popup import Popup        # Для создания всплывающих окон
from kivy.uix.widget import Widget      # Базовый класс для виджетов

# Устанавливаем цвет фона окна (светло-голубой)
Window.clearcolor = (0.902, 0.902, 0.980, 1)

# Функция проверки введенного числа на соответствие диапазону 1-100
def is_valid(number):
    try:
        return 1 <= int(number) <= 100
    except ValueError:
        return False

# Функция генерации случайного 4-значного числа с неповторяющимися цифрами
def generate():
    first_digits = list(range(1, 10))  # Список цифр от 1 до 9 для первой позиции
    all_digits = list(range(10))       # Список всех цифр от 0 до 9
    shuffle(first_digits)              # Перемешиваем списки
    shuffle(all_digits)
    number = str(first_digits[0])      # Берем первую цифру
    i = 0
    # Добавляем остальные цифры, проверяя на уникальность
    while len(number) != 4:
        if str(all_digits[i]) not in number:
            number += str(all_digits[i])
        else:
            i += 1
    return number

# Экран начальный
class StartScreen(Screen):
    def __init__(self, **kwargs):
        super(StartScreen, self).__init__(**kwargs)

        # Создаем контейнер для элементов экрана
        layout = BoxLayout(orientation='vertical', padding=20, spacing=10)

        # Создаем текстовую метку с приветствием
        welcome_label = Label(
            text="   Добро пожаловать\n в числовую угадайку",
            font_size=55,
            color=(147/255, 112/255, 219/255, 1),
            size_hint=(1, 0.6))
        layout.add_widget(welcome_label)

        # Создаем кнопку "Начать"
        start_button = Button(
            text="Начать",
            font_size=100,
            size_hint=(0.5, 0.4),
            pos_hint={'center_x': 0.5},
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        start_button.bind(on_press=self.start_game)
        layout.add_widget(start_button)

        # Добавляем контейнер в экран
        self.add_widget(layout)

    # Функция перехода на следующий экран при нажатии кнопки "Начать"
    def start_game(self, instance):
        self.manager.current = "choose_level"


# Экран выбора уровня
class ChooseLevelScreen(Screen):
    def __init__(self, **kwargs):
        super(ChooseLevelScreen, self).__init__(**kwargs)
        # Создаем контейнер для элементов экрана
        layout = BoxLayout(orientation='vertical', padding=20, spacing=10)
        # Создаем текстовую метку с выбором уровня
        label = Label(text="Выберите уровень",
            font_size=60,
            color=(147/255, 112/255, 219/255, 1))
        layout.add_widget(label)
        # Создаем кнопку "Уровень 1"
        level1_button = Button(text="Уровень 1",
            font_size=80,
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        level1_button.bind(on_press=self.choose_mode)
        layout.add_widget(level1_button)
        # Создаем кнопку "Уровень 2"
        level2_button = Button( text="Уровень 2",
            font_size=80,
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        level2_button.bind(on_press=self.start_level2)
        layout.add_widget(level2_button)
        # Добавляем контейнер в экран
        self.add_widget(layout)

    # Функция перехода на следующий экран при выборе уровня
    def choose_mode(self, instance):
        self.manager.current = "choose_time_mode"

    # Функция перехода на следующий экран при выборе уровня 2
    def start_level2(self, instance):
        self.manager.current = "level2"


# Экран выбора режима времени
class ChooseTimeModeScreen(Screen):
    def __init__(self, **kwargs):
        super(ChooseTimeModeScreen, self).__init__(**kwargs)
        # Создаем контейнер для элементов экрана
        layout = BoxLayout(orientation='vertical', padding=20, spacing=10)
        # Создаем текстовую метку с выбором режима времени
        label = Label(text="Выберите режим:",
            font_size=60,
            color=(147/255, 112/255, 219/255, 1))
        layout.add_widget(label)
        # Создаем кнопку "С таймером"
        with_timer_button = Button(text="С таймером",
            font_size=80,
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        with_timer_button.bind(on_press=self.start_with_timer)
        layout.add_widget(with_timer_button)
        # Создаем кнопку "Без таймера"
        no_timer_button = Button(text="Без таймера",
            font_size=80,
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        no_timer_button.bind(on_press=self.start_no_timer)
        layout.add_widget(no_timer_button)
        # Добавляем контейнер в экран
        self.add_widget(layout)

    # Функция перехода на следующий экран при выборе режима времени
    def start_with_timer(self, instance):
        game_screen = self.manager.get_screen("game")
        game_screen.time_mode = True
        self.manager.current = "game"

    # Функция перехода на следующий экран при выборе режима времени без таймера
    def start_no_timer(self, instance):
        game_screen = self.manager.get_screen("game")
        game_screen.time_mode = False
        self.manager.current = "game"


# Экран игры
class GameScreen(Screen):
    def __init__(self, **kwargs):
        super(GameScreen, self).__init__(**kwargs)

        # Инициализируем переменные для игры
        self.num = randint(1, 100)
        self.attempts = 0
        self.timer = 30
        self.timer_event = None
        self.time_mode = False

        # Создаем контейнер для элементов экрана
        self.layout = BoxLayout(orientation='vertical', padding=20, spacing=10)

        # Создаем popup для победы
        self.victory_content = BoxLayout(orientation='vertical', spacing=10, padding=10)
        self.victory_label = Label(
            text="",
            font_size=50,
            color=(123/255, 104/255, 238/255, 1),
            halign='center')
        self.victory_content.add_widget(self.victory_label)

        # Создаем кнопку "OK" для popup
        close_button = Button(
            text='OK',
            size_hint=(0.5, 0.3),
            pos_hint={'center_x': 0.5},
            font_size=40,
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        self.victory_content.add_widget(close_button)

        # Создаем popup
        self.victory_popup = Popup(
            title='Поздравляем!',
            title_color=(1, 0.647, 0, 1),
            content=self.victory_content,
            size_hint=(0.8, 0.4),
            background_color=(0.847, 0.749, 0.847, 1),
            background='atlas://data/images/defaulttheme/button',
            auto_dismiss=True)
        close_button.bind(on_press=self.victory_popup.dismiss)

        # Создаем верхнюю панель
        self.top_bar = BoxLayout(orientation='horizontal', size_hint=(1, 0.2), spacing=30)

        # Создаем таймер
        self.timer_label = Label(
            text="30 сек",
            font_size=80,
            color=(75/255, 0/255, 130/255, 1),
            size_hint=(0.4, 1))
        self.top_bar.add_widget(self.timer_label)

        # Создаем пустой Label для выравнивания
        spacer = Label(size_hint=(0.4, 1))
        self.top_bar.add_widget(spacer)

        # Создаем кнопки справа с дополнительным отступом между ними
        buttons_layout = BoxLayout(orientation='horizontal', spacing=40, size_hint=(0.6, 1))

        # Создаем кнопку "Домой"
        self.home_button = Button(
            text="Домой",
            font_size=65,
            size_hint=(0.5, 0.9),
            pos_hint={'center_y': 0.5},
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        self.home_button.bind(on_press=self.go_home)
        buttons_layout.add_widget(self.home_button)

        # Создаем кнопку "Заново"
        self.reset_button = Button(
            text="Заново",
            font_size=65,
            size_hint=(0.5, 0.9),
            pos_hint={'center_y': 0.5},
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        self.reset_button.bind(on_press=self.reset_game)
        buttons_layout.add_widget(self.reset_button)

        # Добавляем кнопки в верхнюю панель
        self.top_bar.add_widget(buttons_layout)

        # Добавляем верхнюю панель в контейнер
        self.layout.add_widget(self.top_bar)

        # Создаем основное поле
        self.label = Label(
            text="Введите число от 1 до 100:",
            font_size=50,
            color=(147/255, 112/255, 219/255, 1))
        self.layout.add_widget(self.label)

        # Создаем поле ввода
        self.input_number = TextInput(
            font_size=50,
            multiline=False,
            input_filter="int",
            size_hint=(1, 0.2))
        self.layout.add_widget(self.input_number)

        # Создаем Label для результата
        self.result_label = Label(text="", font_size=40, color=(123/255, 104/255, 238/255, 1))
        self.layout.add_widget(self.result_label)

        # Создаем кнопку "Проверить"
        self.check_button = Button(
            text="Проверить",
            font_size=50,
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        self.check_button.bind(on_press=self.check_number)
        self.layout.add_widget(self.check_button)

        # Добавляем контейнер в экран
        self.add_widget(self.layout)

    # Функция обновления таймера
    def on_enter(self):
        if self.time_mode:
            self.timer = 30
            self.timer_label.text = "30 сек"
            self.timer_event = Clock.schedule_interval(self.update_timer, 1)
        else:
            self.timer_label.text = ""

    # Функция обновления таймера
    def update_timer(self, dt):
        if not self.time_mode:
            if self.timer_event:
                self.timer_event.cancel()
            return False

        self.timer -= 1
        self.timer_label.text = f"{self.timer} сек"

        if self.timer <= 0:
            if self.timer_event:
                self.timer_event.cancel()
            # Отключаем ввод и кнопку проверки
            self.input_number.disabled = True
            self.check_button.disabled = True
            # Показываем сообщение о завершении времени
            self.victory_label.text = f"Время истекло!\nЗагаданное число: {self.num}"
            self.victory_popup.open()
            return False
        return True

    # Функция перехода на предыдущий экран
    def go_home(self, instance):
        if self.timer_event:
            self.timer_event.cancel()
        self.manager.current = "start"

    # Функция сброса игры
    def reset_game(self, instance=None):
        self.num = randint(1, 100)
        self.attempts = 0
        self.input_number.text = ''
        self.result_label.text = ''
        # Включаем обратно ввод и кнопку
        self.input_number.disabled = False
        self.check_button.disabled = False
        if self.time_mode:
            self.timer = 30
            self.timer_label.text = "30 сек"
            if self.timer_event:
                self.timer_event.cancel()
            self.timer_event = Clock.schedule_interval(self.update_timer, 1)
        else:
            self.timer_label.text = ""
            if self.timer_event:
                self.timer_event.cancel()
                self.timer_event = None

    # Функция проверки введенного числа
    def check_number(self, instance):
        try:
            number = int(self.input_number.text)
            if not is_valid(number):
                self.result_label.text = "Введите число от 1 до 100!"
                return
            self.attempts += 1
            if number < self.num:
                self.result_label.text = "Ваше число меньше"
            elif number > self.num:
                self.result_label.text = "Ваше число больше"
            else:
                self.victory_label.text = f"Вы победили!\nЗагаданное число: {self.num}\nКоличество попыток: {self.attempts}"
                self.victory_popup.open()
                self.reset_game()
            self.input_number.text = ''  # Очищаем поле ввода после каждой проверки
        except ValueError:
            self.result_label.text = "Введите целое число!"


# Экран для уровня 2
class Level2Screen(Screen):
    def __init__(self, **kwargs):
        super(Level2Screen, self).__init__(**kwargs)

        # Инициализируем переменные для игры
        self.secret_number = generate()
        self.attempts = 0
        self.main_layout = BoxLayout(orientation='vertical', padding=10, spacing=10)
        self.victory_content = BoxLayout(orientation='vertical', spacing=10, padding=10)
        self.victory_label = Label(
            text="",
            font_size=50,
            color=(123 / 255, 104 / 255, 238 / 255, 1),
            halign='center')
        self.victory_content.add_widget(self.victory_label)

        # Создаем кнопку "OK" для popup
        close_button = Button(
            text='OK',
            size_hint=(0.5, 0.3),
            pos_hint={'center_x': 0.5},
            font_size=40,
            color=(70 / 255, 130 / 255, 180 / 255, 1),
            background_color=(.8, .5, .4, 0))
        self.victory_content.add_widget(close_button)

        # Создаем popup
        self.victory_popup = Popup(
            title='Поздравляем!',
            title_color=(1, 0.647, 0, 1),
            content=self.victory_content,
            size_hint=(0.8, 0.4),
            background_color=(0.847, 0.749, 0.847, 1),
            background='atlas://data/images/defaulttheme/button',
            auto_dismiss=True)
        close_button.bind(on_press=self.victory_popup.dismiss)

        # Создаем popup с правилами
        popup_layout = BoxLayout(orientation='vertical', size_hint=(1, 1), spacing=10)

        # Создаем контейнер для кнопки X
        top_layout = BoxLayout(orientation='horizontal', size_hint=(1, 0.05))
        close_button = Button(
            text='X',
            size_hint=(None, None),
            size=(30, 30),
            pos_hint={'right': 1, 'top': 1},
            color=(70/255, 130/255, 180/255, 1),
            background_color=(0.847, 0.749, 0.847, 0))
        top_layout.add_widget(Widget())  # Пустой виджет для отступа слева
        top_layout.add_widget(close_button)

        # Текст правил
        rules_text = """
• Зеленый цвет - цифра на правильном месте
• Красный цвет - цифра есть в числе, но не на том месте
• Черный цвет - такой цифры нет в числе
"""
        rules_label = Label(
            text=rules_text,
            text_size=(400, None),  # Ограничение ширины текста
            size_hint_y=None,
            height=200,
            halign='left',
            valign='top',
            color=(1, 0.647, 0, 1),
            padding=(10, 10))
        rules_label.bind(size=lambda instance, value: setattr(instance, 'text_size', (value[0], None)))  # Автоперенос текста

        # Добавляем элементы в popup
        popup_layout.add_widget(top_layout)
        popup_layout.add_widget(rules_label)

        # Создаем popup с правилами
        self.rules_popup = Popup(
            title='Правила игры',
            title_align='center',
            title_color=(0.1, 0.5, 1, 1),  # Синий заголовок
            content=popup_layout,
            size_hint=(0.8, 0.8),
            background_color=(0.847, 0.749, 0.847, 1),
            background='atlas://data/images/defaulttheme/button',
            auto_dismiss=True)

        # Связываем кнопку X с закрытием popup
        close_button.bind(on_press=self.rules_popup.dismiss)

        # Создаем верхнюю панель
        self.top_bar = BoxLayout(orientation='horizontal', size_hint=(1, 0.2), spacing=10)

        # Создаем кнопки в верхней панели
        button_layout = BoxLayout(orientation='horizontal', spacing=10, size_hint=(1, 1))

        # Создаем кнопку "Правила"
        self.rules_button = Button(
            text="Правила",
            font_size=65,
            size_hint=(0.3, 0.9),
            pos_hint={'center_y': 0.5},
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        self.rules_button.bind(on_press=lambda x: self.rules_popup.open())
        button_layout.add_widget(self.rules_button)

        # Создаем кнопку "Домой"
        self.home_button = Button(
            text="Домой",
            font_size=65,
            size_hint=(0.3, 0.9),
            pos_hint={'center_y': 0.5},
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        self.home_button.bind(on_press=self.go_home)
        button_layout.add_widget(self.home_button)

        # Создаем кнопку "Заново"
        self.reset_button = Button(
            text="Заново",
            font_size=65,
            size_hint=(0.3, 0.9),
            pos_hint={'center_y': 0.5},
            color=(70/255, 130/255, 180/255, 1),
            background_color=(.8, .5, .4, 0))
        self.reset_button.bind(on_press=self.reset_game)
        button_layout.add_widget(self.reset_button)

        # Добавляем кнопки в верхнюю панель
        self.top_bar.add_widget(button_layout)

        # Увеличиваем spacing между попытками
        self.attempts_layout = BoxLayout(orientation='vertical', size_hint=(1, 0.8), spacing=15)
        self.main_layout.add_widget(self.top_bar)
        self.main_layout.add_widget(self.attempts_layout)

        # Создаем Label для ввода числа
        self.label = Label(text="Введите четырехзначное число:", font_size=50, color=(147/255, 112/255, 219/255, 1))
        self.main_layout.add_widget(self.label)

        # Создаем поле ввода
        self.input_number = TextInput(font_size=60, multiline=False, input_filter='int', size_hint=(1, 0.2))
        self.main_layout.add_widget(self.input_number)

        # Создаем кнопку "Проверить"
        self.check_button = Button(text="Проверить",
                                   font_size=40,
                                   color=(70/255, 130/255, 180/255, 1),
                                   background_color=(.8, .5, .4, 0))
        self.check_button.bind(on_press=self.check_number)
        self.main_layout.add_widget(self.check_button)

        # Добавляем контейнер в экран
        self.add_widget(self.main_layout)

    # Функция перехода на предыдущий экран
    def on_enter(self):
        # Показываем popup при входе на экран
        self.rules_popup.open()

    # Функция перехода на предыдущий экран
    def go_home(self, instance):
        self.manager.current = 'start'

    # Функция сброса игры
    def reset_game(self, instance=None):
        # Генерируем новое число
        self.secret_number = generate()
        # Очищаем все попытки
        self.attempts_layout.clear_widgets()
        # Сбрасываем поле ввода
        self.input_number.text = ''
        # Сбрасываем счетчик попыток
        self.attempts = 0

    # Функция проверки введенного числа
    def check_number(self, instance):
        number = self.input_number.text
        if len(number) != 4 or not number.isdigit():
            self.result_label = Label(text="Введите четырехзначное число!", font_size=40, color=(123/255, 104/255, 238/255, 1))
            self.main_layout.add_widget(self.result_label)
            return
        self.attempts += 1
        bulls = 0  # Правильные цифры на правильных местах
        result_layout = BoxLayout(size_hint=(1, None), height=60, spacing=10)
        for i, digit in enumerate(number):
            if digit == self.secret_number[i]:
                color = (0, 1, 0, 1)  # Зеленый
                bulls += 1
            elif digit in self.secret_number: color = (1, 0, 0, 1)  # Красный
            else: color = (0, 0, 0, 1)  # Черный
            digit_label = Label(text=digit, font_size=80, color=color, size_hint=(0.2, 1))
            result_layout.add_widget(digit_label)
        if bulls == 4:  # Если все цифры угаданы правильно
            # Очищаем историю попыток
            self.victory_label.text = f"Вы победили!\nЗагаданное число: {self.secret_number}\nКоличество попыток: {self.attempts}"
            self.victory_popup.open()
            self.reset_game()
        self.attempts_layout.add_widget(result_layout)
        self.input_number.text = ''


# ОсновноВе приложение
class GuessGameApp(App):
    def build(self):
        # Создаем менеджер экранов
        sm = ScreenManager()
        
        # Добавляем все экраны в менеджер
        sm.add_widget(StartScreen(name='start'))
        sm.add_widget(ChooseLevelScreen(name='choose_level'))
        sm.add_widget(ChooseTimeModeScreen(name='choose_time_mode'))
        sm.add_widget(GameScreen(name='game'))
        sm.add_widget(Level2Screen(name='level2'))
        return sm

# Запускаем приложение, если файл запущен напрямую
if __name__ == "__main__":
    GuessGameApp().run()
