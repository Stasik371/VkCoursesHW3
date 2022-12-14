# VkCoursesHW3
HomeWork JUnit5+Mockito for VK
Цель домашнего задания - научиться программировать, покрывая код юнит- и интеграционными тестами. Изучить приемы замены программного кода в тестовом окружении. Освоить основное API библиотек Junit и Mockito.
- - - - - - -
### Общие требования
  Используя наработки из домашнего задания 1, необходимо написать приложение “Библиотека”. Одноименный контроллер “Библиотека” хранит получаемые книги в ячейках, заполняя их по порядку от первой до последней. 
Количество ячеек и фабрика книг задаются в конструкторе класса, там же происходит заполнение. Если ячеек недостаточно, контроллер бросает исключение. Конструктор может иметь дополнительные параметры.

  Библиотека имеет методы “взять книгу” с параметром “номер ячейки” и “добавить книгу” с параметром типа “Книга”. Если книга берется из библиотеки, то в консоль выводится информация о номере ячейки и взятой книге. Если книга добавляется в библиотеку, то она ставится в первую по порядку свободную ячейку. Если свободных ячеек нет, то контроллер бросает исключение. 
Библиотека также имеет метод “напечатать в консоль содержимое”.

  Фабрика библиотеки имеет метод “создать библиотеку заданной вместимости”. Список книг загружается из файла со списком сериализованных книг. 
Итоговое консольное приложение и принимает два параметра: полный путь к файлу и вместимость библиотеки (количество ячеек). Приложение создает библиотеку, и выводит в консоль список книг в ячейках.
Приложение использует DI фреймворк Guice. Необходимо покрыть код тестами, также используя Guice в тестовой среде.
- - - - - - - -
### Список сценариев, для которых нужно создать автотесты:
Библиотека бросает исключение при создании, если ее вместимость меньше чем количество книг, возвращаемое фабрикой.
При создании библиотеки все книги расставлены по ячейкам в порядке как они возвращаются фабрикой книг. Остальные ячейки пусты.
При взятии книги информация о ней и ячейке выводится.
При попытке взять книгу из пустой ячейки библиотека бросает исключение.
При взятии книги возвращается именно та книга, что была в этой ячейке.
При добавлении книги она размещается в первой свободной ячейке.
Если при добавлении книги свободных ячеек нет, библиотека бросает исключение.
Вызов метода “напечатать в консоль содержимое” выводит информацию о содержимом ячеек библиотеки.
