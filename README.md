# 이팔청춘

 어르신들의 회상을 통한 치료서비스 프로젝트
 

# 프로젝트 설명

### 개발환경

   - Android Studio : 3.6.2
   
   - Language : Kotlin
   
   - Version Control : Git
   
   
### 프로젝트구조

 - Architecture Model : MVVM
 
 - Architecture Component : Retrofit2, Okhttp3, Coroutine, LiveData, Dagger2, Room, ViewBinding
 
 - Library : Glide, ViewPager2, Exoplayer2
 
 
 # Release Version
 
  ### Test
   - 1.0.3(2020.06.08) : 밀도 ->  기기 크기별 레이아웃 대응 수정
   
   - 1.0.2(2020.06.05) : 기기 밀도별 레이아웃 대응, 프로필 편집버튼 제거, 카운트타이머 ui 변경
   
   - 1.0.1(2020.05.29) : 초기 개발 완성버전
   
   - 1.0.0(2020.05.17) : ui 완성 버전(서버작업 연동x)
   
  
  ### Production
  
  
  
  # Bug & Fix
   1. recyclerview item notify 할때 payload를 설정하지 않으면 기존의 뷰 위에 새로운 뷰가 생성된다. 기존의 뷰를 가리키던 변수는 이 현상때문에 엉뚱한 주소를 가리키고 있어 뷰를 바인딩 할때 payload를 걸어서 재생성이 아닌 업데이트를 시켜야 한다.
   
   2. 불필요한 resource를 줄이기 위해 shrinkresources 기능을 사용하면 네트워크로 응답한 결과를 매핑하지 못하는 현상 발생. 이유는 컴파일시 참조되지 않는 객체에 대해서 해당 코드를 지우므로 일어난다. 코드축소의 대상에서 제외시키려면 해당 모델클래스에 @Keep 어노테이션을 추가해야한다. https://stackoverflow.com/questions/60314747/minifyenabled-true-and-shrinkresources-true-in-release-build-retrofit-apis-are[https://stackoverflow.com/questions/60314747/minifyenabled-true-and-shrinkresources-true-in-release-build-retrofit-apis-are]
