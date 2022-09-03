import { Box, Button, Container, TextField, ThemeProvider, Typography } from '@mui/material'
import type { NextPage } from 'next'
import { useState } from 'react'
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
        <Button
          variant='contained'
          onClick={() => {
            copyToClipboard(outText)
          }}
        >
          クリップボードにコピー
        </Button>
      </Container>
    </ThemeProvider>
  )
}

export default Home
