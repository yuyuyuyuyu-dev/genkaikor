import { TextField, Typography } from '@mui/material'
import type { NextPage } from 'next'
import { useState } from 'react'
import insertDakuten from '../utils/insertDakuten'

const Home: NextPage = () => {
  const [outText, setOutText] = useState('')
  return (
    <>
      <TextField
        onChange={(e) => {
          setOutText(insertDakuten(e.target.value))
        }}
      >
      </TextField>
      <Typography>{outText}</Typography>
    </>
  )
}

export default Home
