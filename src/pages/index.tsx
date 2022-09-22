import { Box, Button, Container, TextField, ThemeProvider, Typography } from '@mui/material'
import type { NextPage } from 'next'
import { useState } from 'react'
import CustomizedSnackbars from '../components/CustomizedSnackbars'
import HeadWithOGP from '../components/HeadWithOGP'
import theme from '../theme/theme'
import copyToClipboard from '../utils/copyToClipboard'
import insertDakuten from '../utils/insertDakuten'

const Home: NextPage = () => {
  const [outText, setOutText] = useState('')
  return (
    <ThemeProvider theme={theme}>
      <HeadWithOGP
        url='https://yu-ko-ba.github.io/genkaikor/'
        title='genkaikor'
        description='入力された文字列に濁点を付けて返します'
        imageUrl='https://raw.githubusercontent.com/yu-ko-ba/genkaikor/main/screenshot.png'
      />
      <main>
        <Container maxWidth="xs">
          <TextField
            onChange={(e) => {
              setOutText(insertDakuten(e.target.value))
            }}
          >
          </TextField>
          <Typography>{outText}</Typography>
          <CustomizedSnackbars
            buttonLabel='クリップボードにコピー'
            snackbarMessage='コピーしました'
            onClick={() => {
              copyToClipboard(outText)
            }}
          />
        </Container>
      </main>
    </ThemeProvider>
  )
}

export default Home
