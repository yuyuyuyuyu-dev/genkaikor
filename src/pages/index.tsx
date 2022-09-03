import { Box, Button, Container, TextField, ThemeProvider, Typography } from '@mui/material'
import type { NextPage } from 'next'
import { useState } from 'react'
import CustomizedSnackbars from '../components/CustomizedSnackbars'
import theme from '../theme/theme'
import copyToClipboard from '../utils/copyToClipboard'
import insertDakuten from '../utils/insertDakuten'

const Home: NextPage = () => {
  const [outText, setOutText] = useState('')
  return (
    <ThemeProvider theme={theme}>
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
    </ThemeProvider>
  )
}

export default Home
