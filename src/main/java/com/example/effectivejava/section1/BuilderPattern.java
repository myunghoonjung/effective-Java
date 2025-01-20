package com.example.effectivejava.section1;

//빌더 패턴 예제
public class BuilderPattern {

 public static void main(String[] args) {
     // 빌더를 사용하여 객체 생성
     NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
             .calories(100)
             .sodium(35)
             .carbohydrate(27)
             .build();

     System.out.println(cocaCola);

     // 선택적인 매개변수를 일부만 설정
     NutritionFacts water = new NutritionFacts.Builder(500, 0)
             .calories(0)
             .build();

     System.out.println(water);
 }
}

//빌더 패턴을 활용한 클래스
class NutritionFacts {
 // 필수 매개변수
 private final int servingSize;  // 1회 제공량 (mL, 필수)
 private final int servings;     // 총 제공량 (회, 필수)

 // 선택 매개변수
 private final int calories;     // 칼로리 (선택)
 private final int fat;          // 지방 (선택)
 private final int sodium;       // 나트륨 (선택)
 private final int carbohydrate; // 탄수화물 (선택)

 // 빌더 클래스
 public static class Builder {
     // 필수 매개변수
     private final int servingSize;
     private final int servings;

     // 선택 매개변수 - 기본값으로 초기화
     private int calories = 0;
     private int fat = 0;
     private int sodium = 0;
     private int carbohydrate = 0;

     public Builder(int servingSize, int servings) {
         this.servingSize = servingSize;
         this.servings = servings;
     }

     public Builder calories(int val) {
         calories = val;
         return this;
     }

     public Builder fat(int val) {
         fat = val;
         return this;
     }

     public Builder sodium(int val) {
         sodium = val;
         return this;
     }

     public Builder carbohydrate(int val) {
         carbohydrate = val;
         return this;
     }

     public NutritionFacts build() {
         return new NutritionFacts(this);
     }
 }

 private NutritionFacts(Builder builder) {
     servingSize = builder.servingSize;
     servings = builder.servings;
     calories = builder.calories;
     fat = builder.fat;
     sodium = builder.sodium;
     carbohydrate = builder.carbohydrate;
 }

 @Override
 public String toString() {
     return "NutritionFacts{" +
             "servingSize=" + servingSize +
             ", servings=" + servings +
             ", calories=" + calories +
             ", fat=" + fat +
             ", sodium=" + sodium +
             ", carbohydrate=" + carbohydrate +
             '}';
 }
}
