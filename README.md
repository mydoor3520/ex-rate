# ex-rate
환율과 비용 api 연계 web application

# 환율 계산 웹앱

- 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/709d92ea-c886-4a22-84a5-70e6af436d2f/Untitled.png)

- 스프링 사용
- 송금국가는 미국, 호주 두 군데 중 하나를 select box로 선택합니다. 각각 통화는 USD, AUD 입니다.
- 수취국가는 한국, 일본, 필리핀 세 군데 중 하나를 select box로 선택합니다. 각각 통화는 KRW, JPY, PHP 입니다.
- 송금국가와 수취국가를 선택하면 아래 환율이 바뀌어나타나야 합니다. 환율은 1 USD/AUD 기준으로 각각 KRW, JPY, PHP의 대응 금액입니다.
- 송금액을 USD/AUD로 입력하고 Submit을 누르면 아래 다음과 같이 수취금액이 KRW, JPY, PHP 중 하나로 계산되어서 나와야 합니다.
- 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상 되면 콤마를 가운데 찍어 보여줍니다. 예를 들어 1234라면 1,234.00으로 나타냅니다.

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/17aad8fa-5a88-46b8-a988-422b77896ea1/Untitled.png)

- 환율정보는 [https://currencylayer.com/](https://currencylayer.com/) 의 무료 서비스를 이용해서 실시간으로 가져와야 합니다. 웹 서버가 시작될 때 한번 가져와서 계속 사용해도 되고, 매번 새로운 환율 정보를 가져와도 됩니다.
- 새로운 무료 계정을 등록해서 API 키를 받아서 사용하면 됩니다. 샘플로 등록된 계정의 키를 예를 들면 다음과 같습니다. [http://www.apilayer.net/api/live?access_key=ee50cd7cc73c9b7a7bb3d9617cfb6b9c](http://www.apilayer.net/api/live?access_key=ee50cd7cc73c9b7a7bb3d9617cfb6b9c)
- Submit을 누르면 선택된 수취국가와 그 환율, 그리고 송금액을 가지고 수취금액을 계산해서 하단에 보여주면 됩니다. API를 이용해서 서버에서 계산해서 뿌려도 되고 자바스크립트로 미리 가져온 환율을 계산해서 수취금액을 보여줘도 되고 Submit 버튼으로 폼을 submit해서 화면을 새로 그려도 됩니다.
- 수취금액을 입력하지 않거나, 0보다 작은 금액이거나 10,000 USD보다 큰 금액, 혹은 바른 숫자가 아니라면 “송금액이 바르지 않습니다"라는 에러 메시지를 보여줍니다. 메시지는 팝업, 혹은 하단에 빨간 글씨로 나타나면 됩니다.
1. 본 테스트는 지원자의 스프링 숙련도를 보기 위한 테스트이며 핵심 기능을 스프링으로 구현하셔야 합니다.
2. 사용기술은 스프링 4.0이후 버전을 사용하면 어떤 기술을 이용해도 상관없습니다. 스프링 부트를 이용해도 됩니다.
3. 테스트 코드를 만드시면 가산점이 있습니다.
4. 클라이언트 화면은 스프링의 뷰 기술(jsp, thymeleaf 등)을 이용해도 좋고, React나 Angular 등을 이용해도 좋습니다.
5. 작성한 코드는 github에 올리고 조회가능한 주소를 보내주면 됩니다.

[작업 계획](https://www.notion.so/c5cbf1f076ee4663bb2d856c53c177a7)
