const insertDakuten = (str: string): string => { 
  const ignores = ['ー', '〜', '!', '?', '！', '？', '\n']
  let result = ""
  for (const s of str) {
    if (ignores.includes(s)) {
      result += s
    } else {
      result += `${s}゛`
    }
  }

  return result
}

export default insertDakuten
