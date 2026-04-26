package com.geroybranda.hero.domain

import com.geroybranda.hero.data.HeroEntity
import kotlin.random.Random

data class GeneratedHero(
    val name: String,
    val emoji: String,
    val appearance: String,
    val personality: String,
    val mission: String,
    val brandRole: String,
    val cartoonIdea: String,
    val sphereLabel: String
) {
    fun displayName(): String = "$name $emoji"

    fun toEntity(isFavorite: Boolean = false): HeroEntity = HeroEntity(
        name = displayName(),
        appearance = appearance,
        personality = personality,
        mission = mission,
        brandRole = brandRole,
        cartoonIdea = cartoonIdea,
        sphereLabel = sphereLabel,
        emoji = emoji,
        isFavorite = isFavorite
    )
}

object HeroGenerator {

    private val emojis = listOf("☀️", "✨", "🌟", "💫", "🎯", "🌈", "🦊", "🤖", "🧸", "☕", "💇", "🌿", "🎨", "🚀", "💜")

    private val namesBySphere: Map<BusinessSphere, List<Pair<String, String>>> = mapOf(
        BusinessSphere.CHILD_CLINIC to listOf(
            "Доктор Лучик" to "☀️",
            "Малыш Плюсик" to "🩹",
            "Сестричка Облако" to "☁️",
            "Профессор Тепло" to "🧡",
            "Дружок Иголка" to "💉"
        ),
        BusinessSphere.SCHOOL to listOf(
            "Учитель Звёздочка" to "⭐",
            "Знайка Книжка" to "📚",
            "Капитан Урок" to "🎓",
            "Сова Мудрость" to "🦉",
            "Радуга Знаний" to "🌈"
        ),
        BusinessSphere.CAFE to listOf(
            "Бариста Карамелька" to "☕",
            "Пекарь Уют" to "🥐",
            "Капучино Кот" to "🐱",
            "Шеф Вкусняшка" to "👨‍🍳",
            "Латте Луч" to "✨"
        ),
        BusinessSphere.TOY_SHOP to listOf(
            "Мишка Радость" to "🧸",
            "Конструктор Клик" to "🧱",
            "Принцесса Подарков" to "🎁",
            "Капитан Игрушек" to "🎮",
            "Зайка Пружинка" to "🐰"
        ),
        BusinessSphere.BEAUTY_SALON to listOf(
            "Мисс Глянец" to "💅",
            "Стилист Сияние" to "✨",
            "Леди Камелия" to "🌸",
            "Маэстро Локон" to "💇",
            "Визажист Звезда" to "⭐"
        ),
        BusinessSphere.SANATORIUM to listOf(
            "Доктор Бриз" to "🌿",
            "Санаторийный Друг" to "🏔️",
            "Няня Здоровье" to "💚",
            "Профессор Воздух" to "🫧",
            "Гид Гармония" to "🧘"
        ),
        BusinessSphere.OTHER to listOf(
            "Амбассадор Бренда" to "🎯",
            "Герой Истории" to "📖",
            "Маскот Успеха" to "🏆",
            "Друг Клиента" to "🤝",
            "Звезда Компании" to "🌟"
        )
    )

    fun generate(
        sphere: BusinessSphere,
        personality: HeroPersonality,
        style: VisualStyle,
        color: HeroColorTone,
        random: Random = Random.Default
    ): GeneratedHero {
        val pool = namesBySphere.getValue(sphere)
        val (baseName, nameEmoji) = pool[random.nextInt(pool.size)]
        val accentEmoji = emojis[random.nextInt(emojis.size)]

        val appearance = buildAppearance(sphere, style, color, random)
        val personalityText = buildPersonality(personality, style, random)
        val mission = buildMission(sphere, personality, random)
        val brandRole = buildBrandRole(sphere, personality, random)
        val cartoon = buildCartoon(sphere, style, personality, random)

        return GeneratedHero(
            name = baseName,
            emoji = if (random.nextBoolean()) nameEmoji else accentEmoji,
            appearance = appearance,
            personality = personalityText,
            mission = mission,
            brandRole = brandRole,
            cartoonIdea = cartoon,
            sphereLabel = sphere.label
        )
    }

    private fun colorPhrase(color: HeroColorTone, random: Random): String {
        val phrases = when (color) {
            HeroColorTone.BLUE -> listOf("в нежно-голубых тонах", "с голубыми акцентами", "в оттенках неба")
            HeroColorTone.GREEN -> listOf("в свежих зелёных тонах", "с природными зелёными акцентами", "в мятных оттенках")
            HeroColorTone.YELLOW -> listOf("в солнечно-жёлтых тонах", "с тёплыми жёлтыми деталями", "в золотистой гамме")
            HeroColorTone.PINK -> listOf("в мягко-розовой палитре", "с нежно-розовыми акцентами", "в пудрово-розовых тонах")
            HeroColorTone.BRAND -> listOf("в фирменных цветах бренда", "с фирменными цветовыми акцентами", "в палитре вашего логотипа")
        }
        return phrases[random.nextInt(phrases.size)]
    }

    private fun stylePhrase(style: VisualStyle, random: Random): String {
        return when (style) {
            VisualStyle.ANIMAL -> listOf(
                "Пушистый зверёк с выразительными глазами и дружелюбной улыбкой.",
                "Компактный звериный персонаж с характерным силуэтом и узнаваемыми ушами.",
                "Милый зверёк в мини-форме, удобный для анимации и стикеров."
            ).random(random)

            VisualStyle.ROBOT -> listOf(
                "Гладкий робот с мягкими формами и «живыми» индикаторами-эмоциями.",
                "Дружелюбный робот-помощник с округлыми панелями и светящимися элементами.",
                "Футуристичный, но тёплый робот без агрессивных углов — идеален для детской аудитории."
            ).random(random)

            VisualStyle.HUMANOID -> listOf(
                "Упрощённый человечек с большой головой, выразительными бровями и яркой одеждой.",
                "Мультяшный человечек в фирменной форме или аксессуарах бренда.",
                "Симпатичный персонаж-человечек, легко адаптируемый под разные сценарии."
            ).random(random)

            VisualStyle.FAIRY_TALE -> listOf(
                "Сказочный персонаж с узнаваемым силуэтом: накидка, корона или волшебный атрибут.",
                "Герой из доброй сказки — мягкие линии, сказочные детали без перегруза.",
                "Персонаж с «книжной» эстетикой: понятен детям и взрослым."
            ).random(random)

            VisualStyle.MAGICAL -> listOf(
                "Волшебное существо со светящимися частицами и мягким свечением контура.",
                "Мистический, но добрый образ с магическим реквизитом и лёгким сиянием.",
                "Существо из мира чудес — лёгкость, воздушность, запоминающийся силуэт."
            ).random(random)
        }
    }

    private fun buildAppearance(
        sphere: BusinessSphere,
        style: VisualStyle,
        color: HeroColorTone,
        random: Random
    ): String {
        val color = colorPhrase(color, random)
        val style = stylePhrase(style, random)
        val context = when (sphere) {
            BusinessSphere.CHILD_CLINIC -> "Образ гармонично вписывается в медицинскую среду без страха и холода."
            BusinessSphere.SCHOOL -> "Внешность поддерживает атмосферу роста, поддержки и любопытства."
            BusinessSphere.CAFE -> "Визуально ассоциируется с ароматом, теплом и гостеприимством."
            BusinessSphere.TOY_SHOP -> "Яркий, но не кричащий — хочется потрогать и забрать домой."
            BusinessSphere.BEAUTY_SALON -> "Элегантность и лёгкость, подчёркивающие заботу о себе."
            BusinessSphere.SANATORIUM -> "Спокойствие, здоровье и природная гармония в деталях."
            BusinessSphere.OTHER -> "Универсальный образ, который легко адаптировать под ваш нишевый стиль."
        }
        return "Внешность: $color. $style $context"
    }

    private fun buildPersonality(
        personality: HeroPersonality,
        style: VisualStyle,
        random: Random
    ): String {
        val core = when (personality) {
            HeroPersonality.KIND -> "Тёплый, внимательный, никогда не осуждает — только поддерживает."
            HeroPersonality.CHEERFUL -> "Солнечный характер, шутки в меру, заряжает позитивом без навязчивости."
            HeroPersonality.SMART -> "Собранный, любознательный, любит объяснять простыми словами."
            HeroPersonality.CARING -> "Заботится о комфорте других, замечает мелочи и предвосхищает желания."
            HeroPersonality.BRAVE -> "Уверенный, готовый помочь в сложной ситуации, вдохновляет действовать."
            HeroPersonality.ENERGETIC -> "Бодрый, динамичный, ведёт за собой и не даёт скучать."
        }
        val styleHint = when (style) {
            VisualStyle.ANIMAL -> "Живые эмоции читаются в ушах, хвосте и позе."
            VisualStyle.ROBOT -> "Эмоции передаются светом, звуками и микроанимацией корпуса."
            VisualStyle.HUMANOID -> "Мимика и жесты максимально понятны широкой аудитории."
            VisualStyle.FAIRY_TALE -> "Характер раскрывается через сказочные метафоры и маленькие истории."
            VisualStyle.MAGICAL -> "Настроение создаётся магией деталей: частицы, свечение, лёгкое движение."
        }
        return "$core $styleHint"
    }

    private fun buildMission(
        sphere: BusinessSphere,
        personality: HeroPersonality,
        random: Random
    ): String {
        val missionStem = when (sphere) {
            BusinessSphere.CHILD_CLINIC -> listOf(
                "Помогает детям не бояться процедур и чувствовать себя героями визита.",
                "Провожает маленького пациента по клинике и объясняет всё по-дружески.",
                "Создаёт ощущение безопасности и заботы с первых секунд в клинике."
            )

            BusinessSphere.SCHOOL -> listOf(
                "Мотивирует учиться, пробовать новое и не стесняться вопросов.",
                "Поддерживает на переменах и напоминает, что ошибки — это шаг к росту.",
                "Делает школу местом открытий, а не только оценок."
            )

            BusinessSphere.CAFE -> listOf(
                "Рассказывает истории блюд и создаёт уют, куда хочется вернуться.",
                "Помогает гостю быстро сориентироваться в меню и почувствовать гостеприимство.",
                "Связывает бренд кафе с тёплыми эмоциями и маленькими радостями дня."
            )

            BusinessSphere.TOY_SHOP -> listOf(
                "Помогает выбрать игрушку по возрасту и интересам, без давления.",
                "Превращает визит в магазин в маленькое приключение.",
                "Объясняет родителям ценность игры и развития через простые образы."
            )

            BusinessSphere.BEAUTY_SALON -> listOf(
                "Передаёт уверенность и заботу о себе без навязчивых стандартов красоты.",
                "Сопровождает клиента в атмосфере релакса и внимания к деталям.",
                "Показывает салон как место восстановления сил и настроения."
            )

            BusinessSphere.SANATORIUM -> listOf(
                "Напоминает о ценности отдыха, режима и маленьких ритуалов здоровья.",
                "Помогает почувствовать программу отдыха лёгкой и понятной.",
                "Связывает образ санатория с природой, воздухом и заботливой командой."
            )

            BusinessSphere.OTHER -> listOf(
                "Формирует доверие к бренду через простые и человечные истории.",
                "Объясняет ценность продукта или услуги без сложного жаргона.",
                "Становится «лицом» компании в соцсетях и на мероприятиях."
            )
        }
        val tone = when (personality) {
            HeroPersonality.KIND -> "Всегда на стороне клиента."
            HeroPersonality.CHEERFUL -> "Добавляет лёгкости каждому контакту."
            HeroPersonality.SMART -> "Опирается на факты, но говорит просто."
            HeroPersonality.CARING -> "Делает акцент на комфорте и индивидуальности."
            HeroPersonality.BRAVE -> "Поддерживает в нестандартных ситуациях."
            HeroPersonality.ENERGETIC -> "Задаёт динамику и интерес к бренду."
        }
        return "${missionStem.random(random)} $tone"
    }

    private fun buildBrandRole(
        sphere: BusinessSphere,
        personality: HeroPersonality,
        random: Random
    ): String {
        val roles = listOf(
            "Узнаваемый герой, который объединяет рекламу, соцсети и офлайн-материалы.",
            "Точка эмоционального контакта: его проще запомнить, чем слоган.",
            "Персонаж для серии роликов, стикеров и коллабораций с блогерами.",
            "Визуальный якорь бренда — его можно масштабировать от баннера до мерча."
        )
        val niche = when (sphere) {
            BusinessSphere.CHILD_CLINIC -> "Снижает тревожность и повышает лояльность семей."
            BusinessSphere.SCHOOL -> "Укрепляет доверие родителей и вовлечённость учеников."
            BusinessSphere.CAFE -> "Увеличивает узнаваемость и «хочу вернуться снова»."
            BusinessSphere.TOY_SHOP -> "Помогает выделиться среди конкурентов и объясняет ценности бренда игры."
            BusinessSphere.BEAUTY_SALON -> "Подчёркивает экспертизу и эмоциональный комфорт сервиса."
            BusinessSphere.SANATORIUM -> "Ассоциирует отдых с заботой и профессионализмом."
            BusinessSphere.OTHER -> "Делает коммуникацию человечнее и ближе к аудитории."
        }
        return "${roles.random(random)} $niche Характер «${personality.label.lowercase()}» усиливает это сообщение."
    }

    private fun buildCartoon(
        sphere: BusinessSphere,
        style: VisualStyle,
        personality: HeroPersonality,
        random: Random
    ): String {
        val plots = when (sphere) {
            BusinessSphere.CHILD_CLINIC -> listOf(
                "Короткий мультфильм: герой провожает ребёнка по клинике и превращает страх в игру.",
                "Серия из 6 эпизодов: маленькие истории о том, как не бояться врача.",
                "Музыкальный ролик: герой поёт дружелюбную песенку о здоровье и гигиене."
            )

            BusinessSphere.SCHOOL -> listOf(
                "Мини-сериал о дне из жизни школы, где герой помогает справляться с задачами.",
                "Обучающий мультик с юмором: герой объясняет сложное через метафоры.",
                "История дружбы: герой поддерживает новичка в классе."
            )

            BusinessSphere.CAFE -> listOf(
                "Утренний эпизод: герой будит город ароматом кофе и добрыми словами.",
                "Серия «Рецепт дня» — короткие истории про блюда и их «характер».",
                "Ночной выпуск: уютная сказка о посетителях и их любимых напитках."
            )

            BusinessSphere.TOY_SHOP -> listOf(
                "Приключение в отделе игрушек: герой помогает игрушкам найти дом.",
                "Обзоры в мультяшном стиле: безопасность, возраст, развивающий эффект.",
                "История коллекции: от полки до детской комнаты."
            )

            BusinessSphere.BEAUTY_SALON -> listOf(
                "Ролик-трансформация: день из жизни салона глазами героя-стилиста.",
                "Серия советов в лёгком юморе о уходе без перегруза информацией.",
                "Мини-история клиента: от усталости к сиянию за один визит."
            )

            BusinessSphere.SANATORIUM -> listOf(
                "Путешествие героя по парку процедур — каждый этап как маленькое чудо.",
                "Спокойный мультфильм о дыхании, прогулках и сне как суперсилах.",
                "Семейная история отдыха: герой связывает поколения."
            )

            BusinessSphere.OTHER -> listOf(
                "Бренд-история в 90 секунд: от проблемы к решению с участием героя.",
                "Серия соцроликов, где герой отвечает на частые вопросы клиентов.",
                "Праздничный спецвыпуск с героем как ведущим корпоративного настроения."
            )
        }
        val styleNote = when (style) {
            VisualStyle.ANIMAL -> "Визуальный ряд — мягкая пластика, милые паузы и тактильные детали."
            VisualStyle.ROBOT -> "Анимация акцентирует свет, звук и микроподвижности механизмов."
            VisualStyle.HUMANOID -> "Классическая мультлямка: читаемые эмоции и динамичные сцены."
            VisualStyle.FAIRY_TALE -> "Кинематографичные кадры, тёплая палитра, лёгкая магия."
            VisualStyle.MAGICAL -> "Частицы, свечение, плавные переходы между сценами-грёзами."
        }
        val mood = when (personality) {
            HeroPersonality.KIND -> "Тон — тёплый и поддерживающий."
            HeroPersonality.CHEERFUL -> "Тон — яркий и игривый."
            HeroPersonality.SMART -> "Тон — ясный и вдохновляющий."
            HeroPersonality.CARING -> "Тон — внимательный и уважительный."
            HeroPersonality.BRAVE -> "Тон — уверенный и мотивирующий."
            HeroPersonality.ENERGETIC -> "Тон — динамичный и современный."
        }
        return "${plots.random(random)} $styleNote $mood"
    }

    private fun <T> List<T>.random(r: Random): T = this[r.nextInt(size)]
}
