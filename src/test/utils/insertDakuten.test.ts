import insertDakuten from "../../utils/insertDakuten";

test('引数の文字列に濁点を付けて返してくれるかのテスト', () => { 
  expect(insertDakuten('あああ！！！')).toBe('あ゛あ゛あ゛！！！')
})
