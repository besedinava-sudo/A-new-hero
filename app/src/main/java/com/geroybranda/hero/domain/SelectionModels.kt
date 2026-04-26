package com.geroybranda.hero.domain

enum class BusinessSphere(val label: String) {
    CHILD_CLINIC("Детская клиника"),
    SCHOOL("Школа"),
    CAFE("Кафе"),
    TOY_SHOP("Магазин игрушек"),
    BEAUTY_SALON("Салон красоты"),
    SANATORIUM("Санаторий"),
    OTHER("Другое")
}

enum class HeroPersonality(val label: String) {
    KIND("Добрый"),
    CHEERFUL("Весёлый"),
    SMART("Умный"),
    CARING("Заботливый"),
    BRAVE("Смелый"),
    ENERGETIC("Энергичный")
}

enum class VisualStyle(val label: String) {
    ANIMAL("Зверёк"),
    ROBOT("Робот"),
    HUMANOID("Человечек"),
    FAIRY_TALE("Сказочный персонаж"),
    MAGICAL("Волшебное существо")
}

enum class HeroColorTone(val label: String) {
    BLUE("Голубой"),
    GREEN("Зелёный"),
    YELLOW("Жёлтый"),
    PINK("Розовый"),
    BRAND("Фирменный цвет")
}
