<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <title>Help — How to Use Pahana Edu</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Self-contained styles just for this page -->
  <style>
    :root{
      --bg:#f3ede4;        /* soft sand (matches #e9d3c0 family) */
      --card:#ffffff;
      --text:#2c3e50;
      --muted:#6b7280;
      --brand:#f8b500;     /* your brand amber */
      --brand-ink:#222222; /* navbar dark */
      --accent:#3498db;    /* blue for highlights */
      --ok:#19ae67;
      --shadow:0 8px 24px rgba(0,0,0,.08);
    }

    *{box-sizing:border-box}
    html,body{margin:0;padding:0}
    body{
      font-family: "Poppins", system-ui, -apple-system, Segoe UI, Roboto, Arial, sans-serif;
      color:var(--text);
      background:linear-gradient(135deg, var(--bg) 0%, #efe4d7 100%);
      min-height:100vh;
      display:flex;
      flex-direction:column;
    }

    /* Top bar (text only; no nav links) */
    .topbar{
      position:sticky; top:0; z-index:10;
      background:var(--brand-ink); color:#fff;
      box-shadow:0 2px 8px rgba(0,0,0,.15);
    }
    .topbar .inner{
      max-width:1100px; margin:auto; padding:14px 20px;
      display:flex; align-items:center; justify-content:center;
      gap:12px;
    }
    .logo{font-weight:700; letter-spacing:.3px}
    .logo .mark{color:var(--brand)}

    /* Page container */
    .wrap{max-width:1100px; margin:24px auto; padding:0 16px}

    .hero{
      background: radial-gradient(1200px 380px at 70% -10%, rgba(52,152,219,.12), transparent 60%),
                  radial-gradient(1200px 420px at -10% 90%, rgba(248,181,0,.18), transparent 60%);
      border-radius:24px;
      padding:28px 24px;
      box-shadow: var(--shadow);
    }
    .hero h1{margin:0 0 6px; font-size:clamp(24px, 2.8vw, 34px)}
    .hero p{margin:0; color:var(--muted)}

    /* Stepper */
    .wizard{margin-top:22px; background:var(--card); border-radius:20px; box-shadow: var(--shadow); padding:18px}
    .steps{
      display:flex; align-items:center; gap:12px;
      overflow-x:auto; padding:6px 4px 12px;
      scrollbar-width:thin;
    }
    .dot{
      flex:none; width:38px; height:38px; border-radius:50%;
      display:flex; align-items:center; justify-content:center;
      background:#e5e7eb; color:#374151; font-weight:700;
      box-shadow: inset 0 0 0 2px rgba(0,0,0,.04);
    }
    .dot.active{ background:var(--accent); color:#fff; box-shadow:0 0 0 5px rgba(52,152,219,.18)}
    .bar{height:4px; flex:1; background:#e5e7eb; border-radius:3px}
    .bar.active{background:var(--accent)}

    /* Cards */
    .cards{margin-top:10px}
    .card{
      display:none; background:linear-gradient(180deg,#fff, #fafafa);
      border:1px solid #eef0f3; border-radius:18px; padding:22px;
      box-shadow: var(--shadow);
    }
    .card.active{display:block; animation:fade .18s ease}
    @keyframes fade{from{opacity:0; transform:translateY(4px)} to{opacity:1; transform:none}}

    .badge{
      display:inline-flex; align-items:center; gap:8px;
      font-size:12px; text-transform:uppercase; letter-spacing:.08em;
      background:rgba(248,181,0,.14); color:#966b00;
      border:1px solid rgba(248,181,0,.35);
      border-radius:999px; padding:6px 10px; font-weight:700;
    }
    .card h3{margin:10px 0 6px; font-size:clamp(18px, 2.2vw, 22px)}
    .lead{color:var(--muted); margin:0 0 10px}
    .checklist{margin:6px 0 0 0; padding-left:20px}
    .checklist li{margin:6px 0; line-height:1.5}

    /* Controls */
    .controls{
      display:flex; justify-content:space-between; gap:10px; margin-top:14px
    }
    .btn{
      border:none; cursor:pointer; padding:12px 16px; border-radius:12px;
      font-weight:700; transition:.15s transform ease, .15s opacity ease, .2s box-shadow ease;
      display:inline-flex; align-items:center; gap:8px;
    }
    .btn:active{transform:translateY(1px)}
    .btn:disabled{opacity:.55; cursor:not-allowed}

    .btn-secondary{background:#eef2f7; color:#111827}
    .btn-primary{background:var(--brand); color:#111; box-shadow:0 6px 20px rgba(248,181,0,.35)}
    .btn-primary:hover{box-shadow:0 8px 28px rgba(248,181,0,.45)}

    .hint{margin:10px 0 0; text-align:center; color:var(--muted); font-size:13px}
    .kbd{font-family:ui-monospace, SFMono-Regular, Menlo, monospace;
         padding:2px 6px; border-radius:6px; background:#111; color:#fff}

    /* Footer note */
    .note{
      text-align:center; color:var(--muted); margin:18px 0 6px; font-size:13px
    }

    @media (max-width:640px){
      .controls{flex-direction:column}
      .btn{justify-content:center}
    }
  </style>
</head>
<body>

  <div class="topbar">
    <div class="inner">
      <div class="logo">Pahana <span class="mark">Edu</span> — Customer Help</div>
    </div>
  </div>

  <div class="wrap">
    <div class="hero">
      <h1>How to use the Pahana Edu system</h1>
      <p>Follow these simple steps from creating your account to checking out and logging out.</p>
    </div>

    <div class="wizard" role="region" aria-label="Customer Help Wizard">
      <!-- stepper -->
      <div class="steps" id="stepper" aria-hidden="false">
        <div class="dot">1</div><div class="bar"></div>
        <div class="dot">2</div><div class="bar"></div>
        <div class="dot">3</div><div class="bar"></div>
        <div class="dot">4</div><div class="bar"></div>
        <div class="dot">5</div><div class="bar"></div>
        <div class="dot">6</div><div class="bar"></div>
        <div class="dot">7</div><div class="bar"></div>
        <div class="dot">8</div><div class="bar"></div>
        <div class="dot">9</div>
      </div>

      <!-- cards -->
      <div class="cards" id="cards">
        <!-- 1 -->
        <section class="card" data-step="1">
          <span class="badge">Step 1</span>
          <h3>Sign Up</h3>
          <p class="lead">Create your customer account with username, address, 10-digit phone number, and a password (min 4 chars).</p>
          <ul class="checklist">
            <li>Choose a unique username.</li>
            <li>Phone number must have exactly 10 digits.</li>
            <li>Remember your password for login.</li>
          </ul>
        </section>

        <!-- 2 -->
        <section class="card" data-step="2">
          <span class="badge">Step 2</span>
          <h3>Login</h3>
          <p class="lead">Enter your username and password to access your dashboard.</p>
          <ul class="checklist">
            <li>Use the exact username you registered with.</li>
            <li>Passwords are case-sensitive.</li>
          </ul>
        </section>

        <!-- 3 -->
        <section class="card" data-step="3">
          <span class="badge">Step 3</span>
          <h3>View / Update Profile</h3>
          <p class="lead">Review your profile details. Account Number is read-only; you can update address, phone, and password.</p>
          <ul class="checklist">
            <li>Keep your address and phone current.</li>
            <li>Update password periodically for security.</li>
          </ul>
        </section>

        <!-- 4 -->
        <section class="card" data-step="4">
          <span class="badge">Step 4</span>
          <h3>Browse Books</h3>
          <p class="lead">See all available books with price and stock. Pick your favorites.</p>
          <ul class="checklist">
            <li>Check stock before adding to cart.</li>
            <li>Use search or scan the list to find titles.</li>
          </ul>
        </section>

        <!-- 5 -->
        <section class="card" data-step="5">
          <span class="badge">Step 5</span>
          <h3>Add to Cart</h3>
          <p class="lead">Select quantity and add the book to your cart. You can repeat this for multiple books.</p>
          <ul class="checklist">
            <li>Adjust quantity based on your needs.</li>
            <li>Cart total updates as you add items.</li>
          </ul>
        </section>

        <!-- 6 -->
        <section class="card" data-step="6">
          <span class="badge">Step 6</span>
          <h3>View / Update Cart</h3>
          <p class="lead">Open your cart to review items. Change quantities or remove items if needed.</p>
          <ul class="checklist">
            <li>Line total = price × quantity.</li>
            <li>Grand total is shown at the bottom.</li>
          </ul>
        </section>

        <!-- 7 -->
        <section class="card" data-step="7">
          <span class="badge">Step 7</span>
          <h3>Checkout (Place Order)</h3>
          <p class="lead">Confirm your cart and place the order. Stock reduces automatically.</p>
          <ul class="checklist">
            <li>Make sure quantities are correct before confirming.</li>
            <li>After checkout, you’ll get an order confirmation.</li>
          </ul>
        </section>

        <!-- 8 -->
        <section class="card" data-step="8">
          <span class="badge">Step 8</span>
          <h3>View Orders & Invoice</h3>
          <p class="lead">See your order history. Open any order to view the invoice with itemized details.</p>
          <ul class="checklist">
            <li>Each order shows date, items, and total.</li>
            <li>Use this for your records or re-ordering.</li>
          </ul>
        </section>

        <!-- 9 -->
        <section class="card" data-step="9">
          <span class="badge">Step 9</span>
          <h3>Logout</h3>
          <p class="lead">For your security, log out after you finish shopping—especially on shared devices.</p>
          <ul class="checklist">
            <li>Closing the browser may not always log you out.</li>
            <li>Use the Logout option from your dashboard.</li>
          </ul>
        </section>
      </div>

      <!-- controls -->
      <div class="controls">
        <button class="btn btn-secondary" id="prevBtn" aria-label="Previous step">◀ Previous</button>
        <button class="btn btn-primary" id="nextBtn" aria-label="Next step">Next ▶</button>
      </div>
      <p class="hint">Tip: use <span class="kbd">←</span> & <span class="kbd">→</span> keys to move between steps.</p>
    </div>

    <p class="note">This help section is for customers only.</p>
  </div>

  <script>
    // Wizard logic
    const dots  = Array.from(document.querySelectorAll('.dot'));
    const bars  = Array.from(document.querySelectorAll('.bar'));
    const cards = Array.from(document.querySelectorAll('.card'));
    const prev  = document.getElementById('prevBtn');
    const next  = document.getElementById('nextBtn');

    let i = 0, last = cards.length - 1;

    function render(){
      cards.forEach((c,idx)=> c.classList.toggle('active', idx===i));
      dots.forEach((d,idx)=> d.classList.toggle('active', idx<=i));
      bars.forEach((b,idx)=> b.classList.toggle('active', idx<i));
      prev.disabled = (i===0);
      next.textContent = (i===last) ? 'Finish' : 'Next ▶';
      next.setAttribute('aria-label', (i===last)?'Finish':'Next step');
    }

    prev.addEventListener('click', ()=>{ if(i>0){ i--; render(); }});
    next.addEventListener('click', ()=>{
      if(i<last){ i++; render(); }
      else {
        // Finish: bounce back to first step (no external links needed)
        i = 0; render();
      }
    });

    // Keyboard shortcuts
    window.addEventListener('keydown', (e)=>{
      if(e.key==='ArrowLeft')  prev.click();
      if(e.key==='ArrowRight') next.click();
    });

    // init
    render();
  </script>
</body>
</html>
