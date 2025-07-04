package com.rrs.alias;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;



public class game extends AppCompatActivity {

    // перебирання команд
    private int counter = 0;
    private int max_counter = 1;

    // очки для перемоги
    private final int score_to_win = command_set.score_to_win;

    // таймер.
    static CountDownTimer timer_count;
    private final long timer = command_set.time_to_win + 1;

    // Програвач звуків
    MediaPlayer right_sound, wrong_sound, winner_sound;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Слова
        ArrayList<String> words = new ArrayList<>(Arrays.asList(
                // Персонажі
                "Бандера", "Джонсонюк", "Байрактар", "Бандера - смузi", "Himars", "Зеленський",
                "Залужний", "Пес - патрон", "Чмоня", "Притула", "Буряти", "Арестович", "Привид Києва",
                "Кiм", "Хмельницький", "Гіга",
                // Їжа
                "Борщ", "Сало", "Вареники", "Пампушки", "Холодець", "Плов", "Каша", "Меню", "Вино",
                "Пиво", "Водка", "Хліб", "Коктейль", "Гранат", "Ягода", "Перець", "Сіль", "Креветка",
                "М'ята", "Малина", "Квас", "Морква", "Компот", "Піца", "Хот - дог", "Лате",
                "Цукерка", "Вишня", "Глінтвейн", "Сир", "Кетчуп", "Молоко", "Яйця", "Лікер", "Сироп",
                // Машини
                "Джип", "Мерседес", "Тойота", "БМВ", "Причіп", "Тесла",
                // Емоції
                "Радість", "Сміх", "Кохання", "Гнів", "Дратівливість", "Підступність", "Зрада",
                "Таємниця",
                // Місто
                "Марiуполь", "Iрпiнь", "Буча", "Крим", "Чорнобаївка", "Євросоюз",  "Херсон",
                "Харків", "Київ", "Миколаїв", "Донбас", "Луганськ",
                // Меми
                "Свино - собаки", "Могилiзацiя", "Бандеромобiль", "Дон", "Київ за 3 днi",
                "Сепаратiсти", "Бiолабораторiя", "Орки", "Паляниця", "рускій воєннний корабель",
                "Доброго вечора, ми з України", "Потєрь нєт", "Тікток - війська",
                "Чорний пакет", "Концерт Кобзона",
                // Інше
                "Дiя", "Калина", "Starlink", "Тризуб", "Кристал", "Волейбол",
                "Футбол", "Лікоть", "Ступні", "Диван", "Візок", "Кит", "Горло", "Бамбук", "Пшениця",
                "Ніготь", "Фольга", "Вугілля", "Шампунь", "Порох", "Шкарпетка", "Африканець",
                "Студія", "Кайдани", "Аптека", "Брат", "Спідниця", "Скло", "Кішка", "Кошик",
                "Акваланг", "Зарядка", "Коза", "Файл", "Очерет", "Меблі", "Пінгвін", "Комбайн",
                "Трактор", "Мангал", "Сніг", "Дощ", "Туман", "Сонце", "Місяць", "Рік", "Динамік",
                "Велосипед", "Двір", "Небо", "Конверт", "Журнал", "Відеокамера", "Жилетка", "Лінійка",
                "Спирт", "Пляма", "Світильник", "Пляж", "Наволочка", "Листя",
                "Доміно", "Ацетон", "Умивальник", "Мішок", "Портфель", "Температура", "Мікроскоп",
                "Картуз", "В'язниця", "Шуба", "Конвеєр", "Інтернат", "Спорт", "Долоні", "Талія",
                "Фігура", "Фонтан", "Пил", "Фортеця", "Мантія", "Глядач", "Карусель", "Дракон",
                "Бензоколонка", "Монета", "Пила", "Двері", "Сіно", "Кабан", "Олень", "Вовк",
                "Канал", "Кіно", "Модем", "Віник", "Канат", "Зброя", "Капот", "Двигун", "Головоломка",
                "Череп", "Штанга", "Гантелі", "Волосся", "Картина", "Кабінет", "Меч", "Іграшка",
                "Носоріг", "Вчитель", "Паровоз", "Няня", "Ремінь", "Лисиця", "Змія", "Косметика",
                "Жонглер", "Бензин", "Плем'я", "Восьминіг", "Губка", "Бачок", "Ніс", "Сокира",
                "Село", "Місто", "Аптечка", "Домкрат", "Телевізор", "Монітор", "Мишка", "Клавіатура",
                "Актриса", "Кросворд", "Око", "Вавілон", "Тісто", "Косинка", "Гіпс",
                "Комуналка", "Кобра", "Гільза", "Скальп", "Булава", "Гетьман", "Козак",
                "Кишеня", "Акумулятор", "Гіпербола", "Пальма", "Амортизатор", "Трамвай", "Клоун",
                "Вогонь", "Кофта", "Плівка", "Дальтонік", "Віск", "Договір", "Пенальті", "Пенал",
                "Копія", "Друк", "Книга", "Щітка", "Протигаз", "Асфальт", "Квиток",
                "Ресторан", "Автомагістраль", "Крейда", "Шприц", "Церква", "Трон", "Гамак",
                "Колгосп", "Факс", "Димохід", "Град", "Плакат", "Зірка", "Лампочка", "Дошка",
                "Автосалон", "Басейн", "Русалка", "Кентавр", "Іскра", "Монстр", "Привид", "Тренажер",
                "Лісопилка", "Гарпун", "Фартух", "Дисплей", "Шолом", "Отаман", "Карієс", "Педаль",
                "Блокнот", "Павук", "Водорість", "Оптика", "Лінза", "Ставок", "Річка",
                "Піжама", "Яр", "Дах", "Портмоне", "Ковчег", "Лійка", "Масло", "Поле",
                "Незалежність", "Гусь", "Тополя", "Дуб", "Соловей", "Вишиванка", "Карпати", "Дума",
                "Вечорниці", "Коса", "Стрічка", "Голод", "Голодомор", "Дзюрчання", "Ворог", "Рій",
                "Свічка", "Сірник", "Телефон", "Пошта", "Виручати", "Допомагати", "Листоноша",
                "Цигарки", "Горнятко", "Смаколик", "Звільнення", "Веселка", "Лікарня", "Залізниця",
                "Бібліотека", "Дриготіти", "Краля", "Обопільно", "Пройдисвіт", "Магніт", "Родзинки",
                "Ровер", "Хутко", "Відчайдух", "Чудернацький", "Дедлайн", "Лайк", "Топлес", "Барбершоп",
                "Шлагбаум", "Парковка", "Портфоліо", "Резюме", "Селфі", "Попкорн",
                "Рекрутер", "Руфер", "Чорнобиль", "Пазли", "Скріншот", "Чізкейк", "Бринза", "Пікнік",
                "Пост", "Шопінг", "Бюстгалтер", "Булінг", "Графіті", "Смайл", "Брелок",
                "Ганчірка", "Макулатура", "Макуха", "Прикормка", "Горловина", "Злодійка",
                "Катод", "Балахон", "Рукавиця", "Марево", "Уран", "Свердло", "Табель", "Акушер",
                "Чапля", "Кремінь", "Бал", "Черево", "Чарівник", "Кліщ", "Корівник", "Крихта",
                "Нашатир", "Марс", "Академія", "Поїзд", "Локон", "Кип'ятильник", "Дермантин",
                "Крематорій", "Ароматизатор", "Бджола", "Гречка", "Акварель", "Урна", "Багаття",
                "Хлопчик", "Ельф", "Алича", "Котлован", "Чорнослив", "Весло", "Висотка", "Хміль",
                "Ноутбук", "Калькулятор", "Архітектор", "Комір", "Ялина", "Ковдра", "Діжка", "Піна",
                "Дрозд", "Базар", "Корм", "Пшоно", "Фасад", "Заступник", "Монополія",
                "Магнолія", "Ліфт", "Вітрило", "Кум", "Бійка", "Скрегіт", "Шепіт", "Постанова",
                "Шантаж", "Схема", "Їжа", "Їжак", "Банка", "Куля", "Ромб", "Карта", "Компас", "Смолоскип",
                "Пристрій", "Програма", "Газета", "Блохи", "Спроба", "Нумеравння", "Порядковість",
                "Чорний день", "Запас", "Склад", "Контейнер", "Діагностика", "Проблема", "Проблематичний",
                "Вбивця", "Жах", "Смола", "Бичок", "Занурення", "Зв'язок", "Компост", "Корабель",
                "Яхта", "Мавпа", "Вила", "Мед", "Відмінник", "Зразковість",
                "Ціль", "Мішень", "Муха", "Слон", "Жираф", "Ворота", "Камін", "Дрова", "Зарплата",
                "Квіти", "Встановлення", "Видалення", "Редактор", "Публікація", "Редагування", "Створення",
                "Адаптування", "Розбіжність", "Розбишаки", "Геній", "Гній", "Межа", "Слід", "Рвати",
                "Маніпулювання", "Полювання", "Квадроцикл", "Грип", "Кашель", "Речення", "Заклад",
                "Звук", "Молюск", "Сигнал", "СТО", "Маркет", "Відро", "Аеродром",
                "Загадка", "Міф", "Гладіатори", "Налаштування", "Газ", "Отоплення", "Благодійник",
                "Скарб", "Доярка", "Жменя", "Капіталіст", "Сірка", "Голова", "Щупальце", "Бумеранг",
                "Орден", "Жінка", "Відомість", "Окис", "Підручник", "Сода", "Чугун",
                "Картотека", "Гарбуз", "Антиквар", "Автомеханік", "Ножівка", "Мікстура", "Шорти",
                "Штори", "Мушля", "Богема", "Штопор", "Цифра", "Сом", "Комаха", "Космос", "Циркуль",
                "Багажник", "Сосна", "Контрабанда", "Курятина", "Гайка", "Болт", "Курок",
                "Дятел", "Овечка", "Верба", "Поліно", "Коліно", "Калоша", "Хамелеон",
                "Сорочка", "Намисто", "Притулок", "Кадет", "Гвинт", "Сталь", "Кабачок", "Авіамеханік",
                "Каса", "Парафін", "Барвник", "Ангел охоронець", "Нитка", "Вітрильник", "Компонент",
                "Барабан", "Настоянка", "Дірка", "Шпилька", "Бухта", "Душ", "Коляска", "Мопед",
                "Клумба", "Відмова", "Юшка", "Молокопостачання", "Добивання", "Рибальство",
                "Психіатр", "Конфіскація", "Пральня", "Ректор", "Алкоголізм", "Розклеювання",
                "Родичка", "Голка", "Поетичність", "Стілець", "Банкет", "Граніт", "Ілюстрація",
                "Кобура", "Виноград", "Масажист", "Цвіль", "Джем", "Табло", "Шлюз", "Нектар",
                "Варта", "Вал", "Коала", "Липа", "Згущене молоко", "Мотоцикл", "Петелька",
                "Куб", "Апендицит", "Жук", "Ікона", "Каструля", "Горище", "Вдівець", "Сміття", "Комод",
                "Таблиця", "Слово", "Прилад", "Готель", "Метеоролог", "Метеорит", "Гравій", "Лижа",
                "Міст", "Лісник", "Пароль", "Газель", "Могила", "Мило", "Загін", "Баба",
                "Абетка", "Дача", "Букет", "Чашка", "Полотно", "Безумець", "Віл", "Кіл", "Агент",
                "Пігулка", "Ріг", "Паркан", "Ковпак", "Вишивка", "Ромашка", "Пензлик", "Хльосткість",
                "Паяльник", "Розтрочка", "Насип", "Зачистка", "Дудка", "Розгинання", "Гель", "Завищення",
                "Підтравлювання", "Призупинення", "Вівчарство", "Кретинізм", "Зрушення", "Прощання",
                "Аджика", "Кафе", "Кальцій", "Мухомор", "Сцена", "Гребінець", "Бюро", "Бактерія",
                "Горб", "Чохол", "Метелик", "Одеколон", "Мадагаскар", "Клон", "Житло", "Гірлянда",
                "Оголошення", "Депо", "Парасолька", "Огірок", "Кобила", "Футляр", "Бінокль",
                "Капіталовкладення", "Крапельниця", "Кортеж", "Пантера", "Пліт", "Шарф", "Знахар",
                "Каналізація", "Артефакт", "Уламок", "Слід від укусу", "Сфінкс", "Біолог",
                "Карлик", "Коробка", "Перли", "Дзвіниця", "Канава", "Камера", "Касета", "Купол",
                "Саркофаг", "Хризантема", "Юпітер", "Тумба", "Злива", "Квартал", "Кульок", "Лоджія",
                "Палата", "Оса", "Кільце", "Відьма", "Шабаш", "Хвіртка", "Завірюха", "Карапуз",
                "Спека", "Кричати", "Гривня", "Київська Русь", "Байдикувати", "Колядники", "Різдво",
                "Критикувати", "Чорнозем", "Беріг", "Немічний", "Гомоніти", "Листопад", "Кортіти",
                "Мжичка", "Руханка", "Черствий", "Чарка", "Адреса", "Актуальний", "Тертушка",
                "Жалюзі", "Чипси", "Базіка", "Роли", "Сон", "Катер", "Медаль", "Галявина", "Кий",
                "Більярд", "Дискета", "Зіниця", "Валіза", "Водопровід", "Лісоруб", "Одяг", "Кастет",
                "Піднос", "Аквапарк", "Підкова", "Держава", "Адам", "Апельсин", "Ворожка", "Самоцвіт",
                "Рис", "Крильце", "Імперія", "Авіалайнер", "Пуповина", "Зліпок", "Горила",
                "Лиман", "Бізон", "Жасмин", "Корж", "Шосе", "Перо", "Шабля", "Глечик", "Офіціант",
                "Вішалка", "Кватирка", "Мундштук", "Боєкомплект", "Берлін", "Капіляр",
                "Яблуко", "Маятник", "Балка", "Мойва", "Гороскоп", "Насіння", "Арматура", "Коран",
                "Редька", "Джгут", "Дресирувальник", "Дошкільник", "Ліхтар", "Канделябр", "Множення",
                "Ділення", "Крилатка", "Автобус", "Сукня", "Валик", "Жаба", "Процесор", "Князівство",
                "Рама", "Лупа", "Шкіра", "Клен", "Фломастер", "Колиска", "Крохмаль", "Трава", "Мазь",
                "Буфет", "Перекус", "Фабрика", "Водоспад", "Ствол", "Кут", "Чайка", "Краб",
                "Храм", "Кучер", "Лімузин", "Маляр", "Вінок", "Козуля", "Емблема", "Інжир",
                "Лапка", "Комора", "Принтер", "Сметана", "Колія", "Королева", "Грунт", "Лохина",
                "Будка", "Мережа", "Часник", "Карета", "Вітер", "Лебідка", "Сова", "Документ",
                "Ртуть", "Хутір", "Просо", "Вампір", "Бобер", "Мадам", "Перископ", "Светр", "Лінія",
                "Муза", "Бункер", "Космонавт", "Дно", "Арена", "Кулак", "Коптилка", "Вівсянка",
                "Купа", "Гонг", "Диктофон", "Водень", "Вагон", "Табір", "Зуб", "Ліжко", "Ліцей",
                "Качалка", "Монокль", "Картяр", "Сходи", "Блузка", "Аксельбант", "Кордон", "Тростина",
                "Конкурент", "Мавзолей", "Брама", "Прес", "Старець", "Собака", "Дитина", "Море",
                "Куба", "Зловмисник", "Гай", "Голос", "Таверна", "Навіс", "Кондиціонер",
                "Фен", "Крендель", "Порошок", "Калюжа", "Ганок", "Фанера", "Апарат", "Ємність",
                "Тераса", "Залізо", "Корінь", "Клин", "Пляшка", "Стріла", "Єврей", "Краватка",
                "Булка", "Каскадер", "Мундир", "Голуб", "Аквамарин", "Гаманець", "Рот", "Келих",
                "Долина", "Кропива", "Посмішка", "Детонатор", "Амеба", "Корона", "Гілка",
                "Губа", "Сито", "Закон", "Барон", "Агроном", "Фара", "Дунай", "Гіпопотам", "Водій",
                "Болото", "Бунгало", "Оливка", "Гойдалка", "Куліса", "Акорд", "Пан", "Кріпак",
                "Олігарх", "Атлантика", "Щур", "Комендант", "Гуртожиток", "Бампер", "Спарта",
                "Бандероль", "Трюм", "Полюс", "Папка", "Атлетика", "Бог", "Солома", "Жертва", "Лондон",
                "Пароплав", "Альпініст", "Духовка", "Жид", "Клаптик", "Пітон", "Лайнер", "Скрипка",
                "Бирка", "Жуйка", "Хрест", "Здоров'я", "Оракул", "Палиця", "Ключиця",
                "Білок", "Білка", "Гангстер", "Брокер", "Кімоно", "Вітрина", "Гумка", "Горіх", "Загс",
                "Болванка", "Суверенітет", "Малозначимість", "Вихор", "Методологія", "Дід", "Репресований",
                "Чесалка", "Тренування", "Реквізит", "Дрифтер", "Неслухняність", "Оббивка", "Дублікат",
                "Маркетинг", "Опікунка", "Реалізація", "Рекордистка", "Підсвічування", "Неон",
                "Мандрівник", "Прискорення", "Поліклініка", "Навчання", "Влада", "Приглушення",
                "Лісоторгівля", "Кубань", "Кадило", "Пташеня", "Брошура", "Свинець", "Клапан",
                "Мильниця", "Баклан", "Намордник", "Котлета", "Окоп", "Камуфляж", "Вафля", "Пістолет",
                "Підвал", "Укриття", "Ковзанка", "Ковзани", "Вітамін", "Шкірка", "Кочегар", "Повербанка",
                "Генератор", "Батарейки", "Термос", "Спаржа", "Броколі", "Запальничка",
                "Каремат", "Каре", "Покер", "Фляга", "Мураха", "Корозія", "Іржа", "Мультитул",
                "Ложа", "Колібрі", "Балтика", "Лисина", "Люстерко", "Шкала", "Сухофрукти", "Мансарда",
                "Казанок", "Автограф", "Скотч", "Глушник", "Свисток", "Бронежилет", "Готівка",
                "Бестселер", "Ворсинка", "Лінгвістика", "Куховар", "Крихкість", "Карабін", "Ягнятина",
                "Пліткар", "Заручини", "Саботаж", "Відкликання", "Враженість", "Вижим", "Ореол",
                "Антисептик", "Вірус", "Запобіжність", "Ретельність", "Субстанція", "Лучник", "Вахтер",
                "Студент", "Професіонал", "Регрес", "Вертольотчик", "Сироватка", "Зілля", "Бляшанка",
                "Друкарка", "Відмінок", "Фільтр", "Пічка", "Пінта", "Загоєння", "Ази", "Плетіння",
                "Журналістика", "Пікантність", "Запікати", "Готування", "Мускат", "Жакет", "Нашийник",
                "Губернія", "Скнара", "Граблі", "Маскарад", "Кенгуру", "Популяризація", "Лом",
                "Петарда", "Мазут", "Зараження", "Прикус", "Дзьоб", "Купідон", "Копієчка", "Грілка",
                "Пазух", "Вульгарність", "Тюлень", "Закладка", "Моль", "Льодяник", "Перон", "Регламент",
                "Кузня", "Дощовик", "Корито", "Пульсація", "Декларація", "Конституція", "Перепад",
                "Переклад", "Мотика", "Поліграф", "Автоваги", "Аплікація", "Шовк", "Пряності",
                "Монгол", "Шайба", "Презентабельність", "Інтерн", "Гарантія", "Бородавка", "Аполлон",
                "Простирадло", "Морфій", "Пульт", "Конфорка", "Упаковка", "Скибка", "Альбом", "Сідло",
                "Дегенерат", "Аферист", "Куртка", "Йог", "Каністра", "Баклажка", "Кінець", "Початок",
                "Засувка", "Ранчо", "Петрушка", "Гаага", "Фужер", "Крапелька", "Арбалет", "Пейджер",
                "Тундра", "Пастка", "Маячок", "Йогурт", "Ковбаса", "Страва", "Шезлонг", "Буквар",
                "Закваска", "Амброзія", "Жнива", "Мелодійність", "Браконьєрство", "Поблажка",
                "Визволення", "Романтика", "Лимонка", "Субмарина", "Вовкодав", "Бетонозмішувач",
                "Протест", "Трибуна", "Вболівальники", "Мажор", "Підмога", "Калібрування", "Виліт",
                "Навігатор", "Бурчання", "Спогад", "Корсет", "Кераміка", "Одержимість", "Нестерпний",
                "Нейтралітет", "Кунжут", "Соус", "Жорстокість", "Піщинка", "Сировина", "Зрілість",
                "Обставина", "Ініціатива", "Дикція", "Серпантин", "Компактність", "Насолода",
                "Стрільбище", "Бунт", "Травматолог", "Гадюка", "Скелелах", "Схил", "Гібрид",
                "Електромобіль", "Комбінат", "Галерея", "Інтерв'ю", "Гіпнотизер", "Кодекс", "Підказка",
                "Параграф", "Легенда", "Окупант", "Екскурс", "Ознайомлення", "Страус", "Командир",
                "Ручник", "Борт", "Розмітка", "Розвідка", "Рація", "Вогнемет", "Перехоплення",
                "Кріт", "Худоба", "Актив", "Дротик", "Людожер", "Талон", "Вандалізм", "Точність",
                "Присмак", "Викрадач", "Вечеря", "Сніданок", "Обід", "Прикриття", "Сварка", "Новини",
                "Планктон", "Близкість", "Мазило", "Підписка", "Відхилення", "Погром",
                "Качан", "Вербування", "Родючість", "Шипшина", "Обліпиха", "Імбир", "Флірт", "Феномен",
                "Кабріолет", "Маєток", "Стихія", "Масовка", "Група", "Кипіння", "Клаустрофобія",
                "Нечисть", "Гід", "Міраж", "Неандерталець", "Мітка", "Шасі", "Шампіньйон", "Зцілення",
                "Абордаж", "Парад", "Диплом", "Витяг", "Марля", "Сирена", "Аргумент", "Медитація",
                "Вейп", "Локація", "Природознавство", "Амазонка", "Дебати", "Галушки", "Оболонь",
                "Воїн", "Тракторні війська", "Цигани", "Приліт", "Слов'яни", "Інтелігент", "Супровід",
                "Екзамен", "Сесія", "Хвилювання", "Розпорядок", "Дієтолог", "Калорії", "Інтер'єр",
                "Збереження", "Набіг", "Курорт", "Градієнт", "Брак", "Критика", "Оцет", "Побажання",
                "Тютюн", "Вінегрет", "Комбінезон", "Ракурс", "Бензобак", "Телебачення", "Шеренга",
                "Дует", "Соло", "Песиміст", "Бездара", "Невдаха", "Віддача", "Відставка", "Пластина",
                "Пластилін", "Транспортир", "Підвіконня", "Валюта", "Хімік", "Фініки", "Кокос",
                "Піраміда", "Імла", "Відлік", "Таймер", "Репортаж", "Імідж", "Листування", "Гоління",
                "Пасіка", "Кеглі", "Шар", "Медіум", "Дворецький", "Чужак", "Утікач", "Реаліст",
                "Ініціали", "Підпис", "Ордер", "Користувач", "Полірування", "Нововведення", "Орендар",
                "Рідкість", "Випуск", "Холодний", "Варвар", "Квитанція", "Коментатор", "Мислення",
                "Спадок", "Рулон", "Серветка", "Парфум", "Програміст", "Унікальність", "Продвинутий",
                "Автопілот", "Штучний інтелект", "Робот", "База даних", "Майно", "Еліта", "Мийка",
                "Округ", "Псевдонім", "Планшет", "Нокаут", "Аксесуар", "Ребро", "Ровесник",
                "Однодумці", "Видавець", "Інфляція", "Прибавка", "Додаток", "Пік", "Пам'ятка",
                "Фундамент", "Горизонт", "Фенікс", "Факт", "Ізоляція", "Пандемія", "Прививка",
                "Анфас", "Відкриття", "Темп", "Вступ", "Зміст", "Санаторій", "Льодовик", "Жіночність",
                "Королівство", "Ранець", "Престол", "Битва", "Інкасатор", "Попутник", "Цунамі",
                "Шуруп", "Фішка", "Рентген", "Староста", "Лідер", "Периметр", "Верстат", "Приспів",
                "Іронія", "Шмарклі", "Естафета", "Радар", "Декорація", "Депресія", "Філія", "Невагомість",
                // Війна
                 "ЗСУ",   "ПВО", "Волонтери", "Колаборанти", "Кримський мiст", "Сухпайок",
                "Повітряна тривога", "Джавелін", "Азовсталь",  "Їжак для танків", "Тероборона",
                "Блокпост", "Ядерна зброя", "Бавовна", "Танк", "Міна", "Тепловізор", "Бліндаж",
                "Балаклава", "Берці", "Баул", "Бронетехніка", "Ленд - ліз", "Кулеметник", "Снайпер",
                "Диверсант"
        ));

        // Звуки
        right_sound = MediaPlayer.create(game.this, R.raw.right); // Звук вгадано
        wrong_sound = MediaPlayer.create(game.this, R.raw.wrong); // Звук не вгадано
        winner_sound = MediaPlayer.create(game.this, R.raw.finish);

        String[] command_name = {command_set.command_1, command_set.command_2};
        int[] command_score = {0, 0};

        if(!Objects.equals(command_set.command_3, "")) {
            command_name = new String[]{command_set.command_1, command_set.command_2, command_set.command_3};
            command_score = new int[]{0, 0, 0};
            max_counter = 2;
        }

        if(!Objects.equals(command_set.command_4, "")) {
            command_name = new String[]{command_set.command_1, command_set.command_2, command_set.command_3, command_set.command_4};
            command_score = new int[]{0, 0, 0, 0};
            max_counter = 3;
        }


        // Кнопки
        Button right = findViewById(R.id.right); // Вгадано
        Button wrong = findViewById(R.id.wrong); // Не вгадано
        TextView commands = findViewById(R.id.command); // Команда
        TextView commands_text = findViewById(R.id.command_text); // Команда текст
        TextView score = findViewById(R.id.score); // Очки
        TextView word = findViewById(R.id.words); // Слова
        TextView score_text = findViewById(R.id.score_text); // Очки текст
        TextView winnes_is = findViewById(R.id.winner_is_text); // Текст Перемогла команда
        TextView winner_name = findViewById(R.id.winner_name); // Вікно з виграшною командою
        Button play_againg = findViewById(R.id.play_again); // Реванш
        Button main_menu = findViewById(R.id.back_main_menu); // До головного меню
        TextView background = findViewById(R.id.background); // Задній фон для очків і назви команди
        TextView timer_game = findViewById(R.id.timer); // таймер
        TextView timer_text = findViewById(R.id.timer_text); // таймер текст
        Button begin = findViewById(R.id.start_game); // Кнопка почати

        // Рандом
        Random rand = new Random();

        // Установка команди, очків, таймер, першого слова, таймера
        commands.setText(command_name[counter]);
        score.setText(Integer.toString(command_score[counter]));
        word.setText(words.remove(rand.nextInt(words.size())));
        timer_game.setText(String.valueOf(timer));


        // Кнопка почати + таймер
        int[] finalCommand_score = command_score;
        String[] finalCommand_name = command_name;
        int[] finalCommand_score1 = command_score;
        String[] finalCommand_name1 = command_name;

        begin.setOnClickListener(view -> {
            right.setVisibility(View.VISIBLE);
            wrong.setVisibility(View.VISIBLE);
            word.setVisibility(View.VISIBLE);
            timer_game.setVisibility(View.VISIBLE);
            timer_text.setVisibility(View.VISIBLE);
            begin.setVisibility(View.INVISIBLE);
            // Таймер
                timer_count = new CountDownTimer(timer * 1000, 1000){
                @Override
                public void onTick(long l){

                    // поки працює таймер
                    timer_game.setText(String.valueOf(l / 1000));
                    // якщо вгадав
                    right.setOnClickListener(view -> {

                        if(command_set.sound_set_counter == 1) {
                            // звук
                            if (right_sound.isPlaying()) {
                                right_sound.stop();
                                right_sound.release();
                                right_sound = MediaPlayer.create(game.this, R.raw.right);
                            }
                            right_sound.start();
                        }


                        // рандом убрати слово, плюсуємо очки
                        word.setText(words.remove(rand.nextInt(words.size())));
                        finalCommand_score[counter]++;
                        score.setText(Integer.toString(finalCommand_score[counter]));

                        // якщо набрано очків, то приховуємо все і виводимо перможця
                        if (finalCommand_score[counter] == score_to_win) {
                            if(command_set.sound_set_counter == 1) {
                                // звук
                                if (winner_sound.isPlaying()) {
                                    winner_sound.stop();
                                    winner_sound.release();
                                    winner_sound = MediaPlayer.create(game.this, R.raw.finish);
                                }
                                winner_sound.start();
                            }

                            right.setVisibility(View.INVISIBLE);
                            wrong.setVisibility(View.INVISIBLE);
                            commands.setVisibility(View.INVISIBLE);
                            score.setVisibility(View.INVISIBLE);
                            word.setVisibility(View.INVISIBLE);
                            score_text.setVisibility(View.INVISIBLE);
                            commands_text.setVisibility(View.INVISIBLE);
                            background.setVisibility(View.INVISIBLE);
                            timer_text.setVisibility(View.INVISIBLE);
                            winner_name.setText(finalCommand_name[counter]);
                            winner_name.setVisibility(View.VISIBLE);
                            winnes_is.setVisibility(View.VISIBLE);
                            play_againg.setVisibility(View.VISIBLE);
                            main_menu.setVisibility(View.VISIBLE);
                            begin.setVisibility(View.INVISIBLE);
                            timer_game.setVisibility(View.INVISIBLE);
                            timer_text.setVisibility(View.INVISIBLE);
                        }
                    });


                    // якщо не вгадав
                    wrong.setOnClickListener(view -> {
                        if(command_set.sound_set_counter == 1) {
                            // звук
                            if (wrong_sound.isPlaying()) {
                                wrong_sound.stop();
                                wrong_sound.release();
                                wrong_sound = MediaPlayer.create(game.this, R.raw.wrong);
                            }
                            wrong_sound.start();
                        }

                        // рандом убрати слово
                        word.setText(words.remove(rand.nextInt(words.size())));
                    });


                }
                @Override
                public  void onFinish(){
                    if (finalCommand_score1[counter] == score_to_win) {
                        begin.setVisibility(View.INVISIBLE);
                        timer_game.setVisibility(View.INVISIBLE);
                    } else {
                        // перебирання + кількість команд
                        counter++;
                        if (counter > max_counter) {
                            counter = 0;
                        }
                        // Установка команди і очків
                        right.setVisibility(View.INVISIBLE);
                        wrong.setVisibility(View.INVISIBLE);
                        word.setVisibility(View.INVISIBLE);
                        word.setText(words.remove(rand.nextInt(words.size())));
                        commands.setText(finalCommand_name1[counter]);
                        score.setText(Integer.toString(finalCommand_score1[counter]));
                        begin.setVisibility(View.VISIBLE);
                        timer_game.setVisibility(View.INVISIBLE);
                        timer_text.setVisibility(View.INVISIBLE);
                        timer_game.setText(String.valueOf(timer - 1));

                        begin.setOnClickListener(view1 -> {
                            right.setVisibility(View.VISIBLE);
                            wrong.setVisibility(View.VISIBLE);
                            word.setVisibility(View.VISIBLE);
                            timer_game.setVisibility(View.VISIBLE);
                            timer_text.setVisibility(View.VISIBLE);
                            begin.setVisibility(View.INVISIBLE);
                            timer_count.start();
                        });
                    }
                }
            }.start();

        });


    }

      public void play_again(View view) {
          Intent intent = new Intent(this, command_set.class);
          startActivity(intent);
        }

    public void main_menu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}